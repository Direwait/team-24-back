package ru.team24.service.security;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.entities.WhiteListedToken;
import ru.team24.database.repositories.WhiteListedTokenRepository;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.util.Date;

@Service
public class JwtService {
    @Autowired
    WhiteListedTokenRepository whiteListedTokenRepository;
    @Value("${jwt.configuration.expirationTime}")
    private long expirationTime;
    KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();

    SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return generateTokenByLogin(userDetails.getUsername());
    }

    public String generateTokenByLogin(String login) {
        Date now = new Date();

        io.jsonwebtoken.JwtBuilder builderJws = Jwts.builder();
        builderJws.subject(login);
        builderJws.issuedAt(now);
        builderJws.expiration(new Date(now.getTime() + expirationTime));
        builderJws.signWith(secretKey);
        String jws = builderJws.compact();
        io.jsonwebtoken.JwtBuilder builderJwe = Jwts.builder();
        builderJwe.subject(jws);
        builderJwe.encryptWith(keyPair.getPublic(), Jwts.KEY.RSA1_5, Jwts.ENC.A256GCM);
        String token = builderJwe.compact();
        var whiteListed = new WhiteListedToken();
        whiteListed.setToken(token);
        whiteListedTokenRepository.save(whiteListed);
        return token;
    }

    public String getLoginFromToken(String token) {
        String jws = Jwts.parser().decryptWith(keyPair.getPrivate()).build()
                .parseEncryptedClaims(token)
                .getPayload()
                .getSubject();
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jws).getPayload().getSubject();
    }

    @Transactional
    public void deleteToken(String token) {
        if(whiteListedTokenRepository.existsByToken(token)){
            whiteListedTokenRepository.deleteByToken(token);
        }
    }
}
