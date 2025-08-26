package ru.team24.controller.domain.manager.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.web.PagedResourcesAssembler;
import ru.team24.service.domain.manager.RequestService;
import ru.team24.service.dto.request.RequestDto;
import ru.team24.service.dto.request.RequestWithCandidateDto;
import ru.team24.service.payload.request.RequestStatusRequest;
import ru.team24.service.payload.request.CandidateResponse;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestControllerImplTest {

    @Mock
    private RequestService requestService;

    @Mock
    private PagedResourcesAssembler<RequestWithCandidateDto> assembler;

    @InjectMocks
    private RequestControllerImpl requestController;

    @Test
    void findByRequestId_WithNonExistentId_ShouldReturnOkWithNull() {
        // Arrange
        when(requestService.findByRequestId(anyLong())).thenReturn(null);

        // Act
        ResponseEntity<?> response = requestController.findByRequestId(999L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(requestService).findByRequestId(999L);
    }

    @Test
    void updateRequest_ShouldCallServiceAndReturnOk() {
        // Arrange
        CandidateResponse candidateResponse = new CandidateResponse();
        doNothing().when(requestService).updateRequest(any(CandidateResponse.class));

        // Act
        ResponseEntity<?> response = requestController.updateRequest(candidateResponse);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(requestService).updateRequest(candidateResponse);
    }

    @Test
    void getRequests_WithStatusRequest_WhenRequestPending_ShouldReturnOk() {
        // Arrange
        RequestStatusRequest statusRequest = new RequestStatusRequest();
        when(requestService.isRequestPending(any(RequestStatusRequest.class))).thenReturn(true);

        // Act
        ResponseEntity<?> response = requestController.getRequests(statusRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(requestService).isRequestPending(statusRequest);
    }

    @Test
    void getRequests_WithStatusRequest_WhenRequestNotPending_ShouldReturnNotFound() {
        // Arrange
        RequestStatusRequest statusRequest = new RequestStatusRequest();
        when(requestService.isRequestPending(any(RequestStatusRequest.class))).thenReturn(false);

        // Act
        ResponseEntity<?> response = requestController.getRequests(statusRequest);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(requestService).isRequestPending(statusRequest);
    }

    @Test
    void deleteRequest_ShouldCallSoftDeleteAndReturnOk() {
        // Arrange
        doNothing().when(requestService).softDeleteRequest(anyLong());

        // Act
        ResponseEntity<?> response = requestController.deleteRequest(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(requestService).softDeleteRequest(1L);
    }

    @Test
    void updateRequestByRequestId_ShouldReturnNull() {
        // This method returns null in the implementation
        ResponseEntity<?> response = requestController.updateRequestByRequestId(1L, new RequestDto());
        assertNull(response);
    }
}