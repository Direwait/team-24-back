package ru.team24.service.impl.domain.admin;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.team24.database.entities.Sopd;
import ru.team24.database.entities.User;
import ru.team24.database.repositories.SopdRepository;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.mapper.SopdMapper;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SopdServiceImplTest {

    @Mock
    private SopdRepository sopdRepository;

    @Mock
    private SopdMapper sopdMapper;

    @InjectMocks
    private SopdServiceImpl sopdService;

    private Sopd sopdEntity;
    private SopdDto sopdDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();

        sopdEntity = Sopd.builder()
                .sopdId(1L)
                .user(user)
                .sopdText("Test SOPD")
                .sopdCreatedAt(new Date())
                .sopdIsActive(true)
                .build();

        sopdDto = new SopdDto();
        sopdDto.setSopdId(1L);
        sopdDto.setSopdText("Test SOPD DTO");
    }

    @Test
    void findRecentSopd_Found() {
        when(sopdRepository.findTopByOrderBySopdIdDesc()).thenReturn(Optional.of(sopdEntity));
        when(sopdMapper.entityToDto(sopdEntity)).thenReturn(sopdDto);

        SopdDto result = sopdService.findRecentSopd();

        assertNotNull(result);
        assertEquals(1L, result.getSopdId());
        verify(sopdRepository).findTopByOrderBySopdIdDesc();
        verify(sopdMapper).entityToDto(sopdEntity);
    }

    @Test
    void findRecentSopd_NotFound() {
        when(sopdRepository.findTopByOrderBySopdIdDesc()).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sopdService.findRecentSopd());
    }

    @Test
    void findSopdById_FoundAndActive() {
        when(sopdRepository.findById(1L)).thenReturn(Optional.of(sopdEntity));
        when(sopdMapper.entityToDto(sopdEntity)).thenReturn(sopdDto);

        SopdDto result = sopdService.findSopdById(1L);

        assertEquals(1L, result.getSopdId());
        verify(sopdRepository).findById(1L);
    }

    @Test
    void findSopdById_NotFoundOrInactive() {
        sopdEntity.setSopdIsActive(false);
        when(sopdRepository.findById(1L)).thenReturn(Optional.of(sopdEntity));

        assertThrows(EntityNotFoundException.class, () -> sopdService.findSopdById(1L));
    }


    @Test
    void createSopd_ShouldSaveAndReturnDto() {
        when(sopdMapper.dtoToEntity(sopdDto)).thenReturn(sopdEntity);
        when(sopdMapper.entityToDto(sopdEntity)).thenReturn(sopdDto);

        SopdDto result = sopdService.createSopd(sopdDto);

        assertEquals(1L, result.getSopdId());
        verify(sopdRepository).save(sopdEntity);
    }

    @Test
    void updateSopd_Found() {
        when(sopdRepository.findById(1L)).thenReturn(Optional.of(sopdEntity));
        when(sopdRepository.save(sopdEntity)).thenReturn(sopdEntity);
        when(sopdMapper.entityToDto(sopdEntity)).thenReturn(sopdDto);

        SopdDto result = sopdService.updateSopd(1L, sopdDto);

        assertEquals(1L, result.getSopdId());
        verify(sopdMapper).updateFromDto(sopdDto, sopdEntity);
    }

    @Test
    void updateSopd_NotFound() {
        when(sopdRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sopdService.updateSopd(1L, sopdDto));
    }

    @Test
    void deleteSopd_Found() {
        when(sopdRepository.findById(1L)).thenReturn(Optional.of(sopdEntity));

        sopdService.deleteSopd(1L);

        assertFalse(sopdEntity.isSopdIsActive());
        verify(sopdRepository).save(sopdEntity);
    }

    @Test
    void deleteSopd_NotFound() {
        when(sopdRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sopdService.deleteSopd(1L));
    }

    @Test
    void deleteSopdReal_ShouldDeleteById() {
        sopdService.deleteSopdReal(1L);

        verify(sopdRepository).deleteById(1L);
    }
}
