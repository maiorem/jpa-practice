package org.maiorem.jpaproject.service;

import org.junit.jupiter.api.Test;
import org.maiorem.jpaproject.dto.GuestbookDTO;
import org.maiorem.jpaproject.entity.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister() {
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title..........")
                .content("Sample Content..............")
                .writer("user0")
                .build();
        System.out.println(service.resister(guestbookDTO));
    }
}
