package org.maiorem.jpaproject.repository;

import org.maiorem.jpaproject.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    
    // 쿼리 메서드
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    // 쿼리 메서드 페이징
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    // 쿼리 메서드 특정 데이터 삭제
    void deleteMemoByMnoLessThan(Long num);
    
}
