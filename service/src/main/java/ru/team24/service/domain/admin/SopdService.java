package ru.team24.service.domain.admin;

import ru.team24.service.dto.SopdDto;

import java.util.List;

public interface SopdService {

    SopdDto findRecentSopd();

    SopdDto findSopdById(long id);

    List<SopdDto> findAllSopds();

    SopdDto createSopd(SopdDto dto);

    SopdDto updateSopd(long id, SopdDto dto);

    void deleteSopd(long id);

    void deleteSopdReal(long id);
}
