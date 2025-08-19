package ru.team24.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api(){

        return new OpenAPI()
                .servers(List.of(new Server()
                                .url("http://localhost:8080")
                        )
                )
                .info(
                        new Info().title("Получение согласия на обработку персональных данных")
                                .version("0.0.1")
                                .description("Тут очень красивый текст описывающий сей прекрасно приложение (описание изменить)")
                );
    }
}
