package org.maiorem.jpaproject.repository;

import org.junit.jupiter.api.Test;
import org.maiorem.jpaproject.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    // 더미 데이터 insert
    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample...."+ i).build();
            memoRepository.save(memo);
        });
    }

    // 아이디로 데이터 select (트랜잭션 x)
    @Test
    public void testSelect() {

        Long mno = 65L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("==============================================");

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    // 실제로 객체를 사용하는 순간에 SQL 동작
    @Transactional
    @Test
    public void testSelect2() {
        Long mno = 78L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("=================================================");

        System.out.println(memo);
        
    }

    @Test
    public void testUpdate() {

        Memo memo = Memo.builder().mno(55L).memoText("Update Text...").build();

        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete() {

        Long mno = 100L;

        memoRepository.deleteById(mno);

    }

    // 페이징 테스트
    @Test
    public void testPageDefault() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("-------------------------------------------------");
        System.out.println("Total Page : " + result.getTotalPages()); // 총 몇페이지
        System.out.println("Total Count : " + result.getTotalElements()); // 전체 갯수
        System.out.println("Page Number : " + result.getNumber()); // 현재 페이지 번호
        System.out.println("Page Size : " + result.getSize()); // 페이지 당 데이터 수
        System.out.println("has Next Page? : " + result.hasNext()); // 다음 페이지 존재 여부
        System.out.println("first Page? : " + result.isFirst()); // 시작페이지(0) 여부
        System.out.println("--------------------------------------------------");
        for (Memo memo : result.getContent()) {
            System.out.println(memo); // 현재 페이지 데이터 출력
        }

    }

}
