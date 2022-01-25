package org.maiorem.jpaproject.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.maiorem.jpaproject.entity.Guestbook;
import org.maiorem.jpaproject.entity.QGuestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title......" + i)
                    .content("Content.........." + i)
                    .writer("user" + (i%10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest() {

        Optional<Guestbook> result = guestbookRepository.findById(300L);

        if(result.isPresent()) {
            Guestbook guestbook = result.get();

            guestbook.changeTitle("Change Title ........... ");
            guestbook.changeContent("Change Content .............");

            guestbookRepository.save(guestbook);
        }

    }

    //WHERE title LIKE '% keyword %'
    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        // 동적 처리를 위해 Q도메인 클래스를 가져와 엔티티의 필드들을 변수로 활용.
        QGuestbook qGuestbook = QGuestbook.guestbook; 

        String keyword = "1";

        // where문에 들어가는 조건들을 넣어주는 컨테이너
        BooleanBuilder builder = new BooleanBuilder(); 

        // 원하는 조건을 필드 값과 결합해서 생성. (keyword가 들어가는 title을 찾기)
        BooleanExpression expression = qGuestbook.title.contains(keyword); 

        // 만들어진 조건을 where 문에 and나 or 같은 키워드와 결합시킴
        builder.and(expression); 

        // builder는 Repository에 추가된 QuerydslPredicateExcutor 인터페이스의 findAll() 사용 가능
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable); 

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach( guestbook -> {
            System.out.println(guestbook);
        });
    }

}
