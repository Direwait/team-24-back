package ru.team24.controller.domain.admin.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.team24.service.domain.admin.SopdService;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.payload.request.SopdUpdateRequest;
import ru.team24.service.security.UserDetailsImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SopdControllerImplTest {

    @Mock
    private SopdService sopdService;

    @InjectMocks
    private SopdControllerImpl sopdController;

    @Test
    void getRecent_ShouldReturnRecentSopd() {
        SopdDto expectedSopd = SopdDto.builder()
                .sopdId(1L)
                .sopdText("Test SOPD text")
                .build();

        when(sopdService.findRecentSopd()).thenReturn(expectedSopd);

        ResponseEntity<SopdDto> response = sopdController.getRecent();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedSopd, response.getBody());
        verify(sopdService, times(1)).findRecentSopd();
    }

    @Test
    void updateSopds_WithAdminAuthority_ShouldUpdateSopd() {
        setupSecurityContextWithAdmin();

        SopdUpdateRequest updateRequest = new SopdUpdateRequest();
        updateRequest.setSopdText("Updated SOPD text");

        SopdDto recentSopd = SopdDto.builder()
                .sopdId(1L)
                .sopdText("Old text")
                .build();

        SopdDto updatedSopd = SopdDto.builder()
                .sopdId(1L)
                .sopdText("Updated SOPD text")
                .build();

        when(sopdService.findRecentSopd()).thenReturn(recentSopd);
        when(sopdService.updateSopd(anyLong(), any(SopdDto.class))).thenReturn(updatedSopd);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);

        ResponseEntity<SopdDto> response = sopdController.updateSopds(userDetails, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated SOPD text", response.getBody().getSopdText());
        verify(sopdService, times(1)).findRecentSopd();
        verify(sopdService, times(1)).updateSopd(eq(1L), any(SopdDto.class));
    }

    @Test
    void deleteSopds_WithAdminAuthority_ShouldDeleteSopd() {
        setupSecurityContextWithAdmin();
        doNothing().when(sopdService).deleteSopd(anyLong());

        ResponseEntity<Void> response = sopdController.deleteSopds(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(sopdService, times(1)).deleteSopd(1L);
    }

    @Test
    void deleteSopdReal_WithAdminAuthority_ShouldPermanentlyDeleteSopd() {
        setupSecurityContextWithAdmin();
        doNothing().when(sopdService).deleteSopdReal(anyLong());

        ResponseEntity<Void> response = sopdController.deleteSopdReal(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(sopdService, times(1)).deleteSopdReal(1L);
    }

    @Test
    void updateSopds_ShouldUseRecentSopdIdForUpdate() {
        setupSecurityContextWithAdmin();
        SopdUpdateRequest updateRequest = new SopdUpdateRequest();
        updateRequest.setSopdText("Updated text");

        SopdDto recentSopd = SopdDto.builder()
                .sopdId(5L)
                .sopdText("Old text")
                .build();

        SopdDto updatedSopd = SopdDto.builder()
                .sopdId(5L)
                .sopdText("Updated text")
                .build();
        when(sopdService.findRecentSopd()).thenReturn(recentSopd);
        when(sopdService.updateSopd(eq(5L), any(SopdDto.class))).thenReturn(updatedSopd);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);


        ResponseEntity<SopdDto> response = sopdController.updateSopds(userDetails, updateRequest);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(sopdService).updateSopd(eq(5L), any(SopdDto.class));
    }

    private void setupSecurityContextWithAdmin() {
        TestingAuthenticationToken authentication = new TestingAuthenticationToken(
                "user",
                "password",
                Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
        );
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}