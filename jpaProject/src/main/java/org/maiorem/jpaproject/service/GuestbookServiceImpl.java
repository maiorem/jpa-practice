package org.maiorem.jpaproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.maiorem.jpaproject.dto.GuestbookDTO;
import org.maiorem.jpaproject.dto.PageRequestDTO;
import org.maiorem.jpaproject.dto.PageResultDTO;
import org.maiorem.jpaproject.entity.Guestbook;
import org.maiorem.jpaproject.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public Long resister(GuestbookDTO dto) {
        log.info("DTO-------------------------------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();

    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity ->
            entityToDto(entity)
        );

        return new PageResultDTO<>(result, fn);
    }
}
