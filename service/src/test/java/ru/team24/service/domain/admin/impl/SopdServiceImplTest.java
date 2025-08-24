package ru.team24.service.domain.admin.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.team24.database.domain.admin.entity.Sopd;
import ru.team24.database.domain.admin.repository.SopdRepository;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.mapper.SopdMapper;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SopdServiceImplTest {

    @Mock
    private SopdRepository sopdRepository;

    @Mock
    private SopdMapper sopdMapper;

    @InjectMocks
    private SopdServiceImpl sopdService;

    private Sopd testSopd;
    private SopdDto testSopdDto;
    private final long TEST_ID = 1L;

    @BeforeEach
    void setUp() {
        testSopd = new Sopd();
        testSopd.setSopdId(TEST_ID);
        testSopd.setSopdIsActive(true);
        testSopd.setSopdCreatedAt(new Date());
        testSopd.setSopdUpdatedAt(new Date());
        testSopd.setSopdLastReview(new Date());

        testSopdDto = new SopdDto();
        testSopdDto.setSopdId(TEST_ID);
        testSopdDto.setSopdIsActive(true);
        testSopdDto.setSopdCreatedAt(new Date());
        testSopdDto.setSopdUpdatedAt(new Date());
        testSopdDto.setSopdLastReview(new Date());
    }

    @Test
    void findSopdById_shouldReturnSopdDto_whenSopdExistsAndActive() {
        when(sopdRepository.findById(TEST_ID)).thenReturn(Optional.of(testSopd));
        when(sopdMapper.entityToDto(testSopd)).thenReturn(testSopdDto);


        SopdDto result = sopdService.findSopdById(TEST_ID);


        assertNotNull(result);
        assertEquals(TEST_ID, result.getSopdId());
        verify(sopdRepository).findById(TEST_ID);
        verify(sopdMapper).entityToDto(testSopd);
        assertNotNull(testSopd.getSopdLastReview());
    }

    @Test
    void findSopdById_shouldThrowException_whenSopdNotFound() {
        when(sopdRepository.findById(TEST_ID)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> sopdService.findSopdById(TEST_ID));
        verify(sopdRepository).findById(TEST_ID);
        verify(sopdMapper, never()).entityToDto(any());
    }

    @Test
    void findSopdById_shouldThrowException_whenSopdNotActive() {

        testSopd.setSopdIsActive(false);
        when(sopdRepository.findById(TEST_ID)).thenReturn(Optional.of(testSopd));


        assertThrows(EntityNotFoundException.class, () -> sopdService.findSopdById(TEST_ID));
        verify(sopdRepository).findById(TEST_ID);
        verify(sopdMapper, never()).entityToDto(any());
    }

    @Test
    void findAllSopds_shouldReturnListOfActiveSopds() {
        Sopd inactiveSopd = new Sopd();
        inactiveSopd.setSopdId(2L);
        inactiveSopd.setSopdIsActive(false);

        List<Sopd> sopds = List.of(testSopd, inactiveSopd);
        when(sopdRepository.findAll()).thenReturn(sopds);
        when(sopdMapper.entityToDto(testSopd)).thenReturn(testSopdDto);


        List<SopdDto> result = sopdService.findAllSopds();


        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TEST_ID, result.get(0).getSopdId());
        verify(sopdRepository).findAll();
        verify(sopdMapper).entityToDto(testSopd);
    }

    @Test
    void createSopd_shouldCreateAndReturnSopdDto() {
        when(sopdMapper.dtoToEntity(testSopdDto)).thenReturn(testSopd);
        when(sopdRepository.save(testSopd)).thenReturn(testSopd);
        when(sopdMapper.entityToDto(testSopd)).thenReturn(testSopdDto);


        SopdDto result = sopdService.createSopd(testSopdDto);


        assertNotNull(result);
        assertEquals(TEST_ID, result.getSopdId());
        verify(sopdMapper).dtoToEntity(testSopdDto);
        verify(sopdRepository).save(testSopd);
        verify(sopdMapper).entityToDto(testSopd);
    }

    @Test
    void updateSopd_shouldUpdateAndReturnSopdDto() {
        SopdDto updatedDto = new SopdDto();

        when(sopdRepository.findById(TEST_ID)).thenReturn(Optional.of(testSopd));
        doNothing().when(sopdMapper).updateFromDto(updatedDto, testSopd);
        when(sopdRepository.save(testSopd)).thenReturn(testSopd);
        when(sopdMapper.entityToDto(testSopd)).thenReturn(testSopdDto);

        SopdDto result = sopdService.updateSopd(TEST_ID, updatedDto);


        assertNotNull(result);
        verify(sopdRepository).findById(TEST_ID);
        verify(sopdMapper).updateFromDto(updatedDto, testSopd);
        verify(sopdRepository).save(testSopd);
        assertNotNull(testSopd.getSopdUpdatedAt());
    }

    @Test
    void updateSopd_shouldThrowException_whenSopdNotFound() {
        when(sopdRepository.findById(TEST_ID)).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> sopdService.updateSopd(TEST_ID, testSopdDto));
        verify(sopdRepository).findById(TEST_ID);
        verify(sopdMapper, never()).updateFromDto(any(), any());
    }

    @Test
    void deleteSopd_shouldSetSopdInactive() {
        when(sopdRepository.getReferenceById(TEST_ID)).thenReturn(testSopd);


        sopdService.deleteSopd(TEST_ID);


        assertFalse(testSopd.isSopdIsActive());
        verify(sopdRepository).getReferenceById(TEST_ID);
    }

    @Test
    void deleteSopdReal_shouldDeleteSopd() {
        sopdService.deleteSopdReal(TEST_ID);


        verify(sopdRepository).deleteById(TEST_ID);
    }

    @Test
    void findRecentSopd_shouldReturnMostRecentSopd() {
        when(sopdRepository.findTopByOrderBySopdIdDesc()).thenReturn(Optional.of(testSopd));
        when(sopdMapper.entityToDto(testSopd)).thenReturn(testSopdDto);


        SopdDto result = sopdService.findRecentSopd();


        assertNotNull(result);
        assertEquals(TEST_ID, result.getSopdId());
        verify(sopdRepository).findTopByOrderBySopdIdDesc();
        verify(sopdMapper).entityToDto(testSopd);
        assertNotNull(testSopd.getSopdLastReview());
    }

    @Test
    void findRecentSopd_shouldThrowException_whenNoSopdsExist() {
        when(sopdRepository.findTopByOrderBySopdIdDesc()).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> sopdService.findRecentSopd());
        verify(sopdRepository).findTopByOrderBySopdIdDesc();
        verify(sopdMapper, never()).entityToDto(any());
    }
}