package ru.team24.service.domain.admin.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team24.database.domain.admin.entity.Sopd;
import ru.team24.database.domain.admin.repository.SopdRepository;
import ru.team24.service.dto.SopdDto;
import ru.team24.service.domain.admin.SopdService;
import ru.team24.service.mapper.SopdMapper;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SopdServiceImpl implements SopdService {

    private final SopdRepository sopdRepository;

    private final SopdMapper sopdMapper;

    @Override
    public SopdDto findSopdById(long id) {
        var sopd = sopdRepository.findById(id).filter(Sopd::isSopdIsActive).orElseThrow(() -> {
                   return new EntityNotFoundException("Sopd with id " + id + " not found");
        });
        sopd.setSopdLastReview(new Date());
        return sopdMapper.entityToDto(sopd);
    }

    @Override
    public List<SopdDto> findAllSopds() {
        List<SopdDto> sopdDtos = sopdRepository
                .findAll()
                .stream()
                .filter(Sopd::isSopdIsActive)
                .map(sopdMapper::entityToDto)
                .toList();

        return sopdDtos;
    }


    @Transactional
    @Override
    public SopdDto createSopd(SopdDto dto) {
        Sopd sopd = sopdMapper.dtoToEntity(dto);
        sopdRepository.save(sopd);

        return sopdMapper.entityToDto(sopd);
    }

    @Override
    public SopdDto updateSopd(long id, SopdDto dto) {
        Sopd sopd = sopdRepository.findById(id)
                .orElseThrow(
                        () -> {
                            return new EntityNotFoundException("Sopd with ID " + id + " dont found");
                        });

        sopdMapper.updateFromDto(dto, sopd);
        sopd.setSopdUpdatedAt(new Date());
        Sopd updatedBook = sopdRepository.save(sopd);

        return sopdMapper.entityToDto(updatedBook);
    }

    @Transactional
    @Override
    public void deleteSopd(long id) {
        Sopd sopd = sopdRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sopd not found with id: " + id));
        sopd.setSopdIsActive(false);
        sopdRepository.save(sopd);
    }

    @Override
    public void deleteSopdReal(long id) {
        sopdRepository.deleteById(id);
    }

    @Override
    public SopdDto findRecentSopd() {
        var sopd = sopdRepository.findTopByOrderBySopdIdDesc().orElseThrow(() -> {
            return new EntityNotFoundException("Recent Sopd not found");
        });
        sopd.setSopdLastReview(new Date());
        return sopdMapper.entityToDto(sopd);
    }
}
