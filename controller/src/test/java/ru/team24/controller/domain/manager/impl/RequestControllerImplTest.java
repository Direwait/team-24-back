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
        when(requestService.findByRequestId(anyLong())).thenReturn(null);


        ResponseEntity<?> response = requestController.findByRequestId(999L);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(requestService).findByRequestId(999L);
    }

    @Test
    void updateRequest_ShouldCallServiceAndReturnOk() {
        CandidateResponse candidateResponse = new CandidateResponse();
        doNothing().when(requestService).updateRequest(any(CandidateResponse.class));


        ResponseEntity<?> response = requestController.updateRequest(candidateResponse);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(requestService).updateRequest(candidateResponse);
    }

    @Test
    void getRequests_WithStatusRequest_WhenRequestPending_ShouldReturnOk() {

        RequestStatusRequest statusRequest = new RequestStatusRequest();
        when(requestService.isRequestPending(any(RequestStatusRequest.class))).thenReturn(true);


        ResponseEntity<?> response = requestController.getRequests(statusRequest);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(requestService).isRequestPending(statusRequest);
    }

    @Test
    void getRequests_WithStatusRequest_WhenRequestNotPending_ShouldReturnNotFound() {

        RequestStatusRequest statusRequest = new RequestStatusRequest();
        when(requestService.isRequestPending(any(RequestStatusRequest.class))).thenReturn(false);


        ResponseEntity<?> response = requestController.getRequests(statusRequest);


        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(requestService).isRequestPending(statusRequest);
    }

    @Test
    void deleteRequest_ShouldCallSoftDeleteAndReturnOk() {

        doNothing().when(requestService).softDeleteRequest(anyLong());


        ResponseEntity<?> response = requestController.deleteRequest(1L);


        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(requestService).softDeleteRequest(1L);
    }
}