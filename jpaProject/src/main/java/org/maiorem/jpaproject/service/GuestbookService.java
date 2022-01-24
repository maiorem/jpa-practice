package org.maiorem.jpaproject.service;

import org.maiorem.jpaproject.dto.GuestbookDTO;
import org.maiorem.jpaproject.entity.Guestbook;

public interface GuestbookService {

    Long resister(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

}
