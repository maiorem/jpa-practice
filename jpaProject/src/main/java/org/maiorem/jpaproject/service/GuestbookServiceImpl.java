package org.maiorem.jpaproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.maiorem.jpaproject.dto.GuestbookDTO;
import org.maiorem.jpaproject.entity.Guestbook;
import org.maiorem.jpaproject.repository.GuestbookRepository;
import org.springframework.stereotype.Service;

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
}
