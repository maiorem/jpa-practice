package org.maiorem.jpaproject.service;

import lombok.extern.log4j.Log4j2;
import org.maiorem.jpaproject.dto.GuestbookDTO;
import org.maiorem.jpaproject.entity.Guestbook;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {

    @Override
    public Long resister(GuestbookDTO dto) {
        log.info("DTO-------------------------------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        return null;

    }
}
