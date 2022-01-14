package org.maiorem.jpaproject.repository;

import org.junit.jupiter.api.Test;
import org.maiorem.jpaproject.entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


}
