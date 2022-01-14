package org.maiorem.jpaproject.repository;

import org.maiorem.jpaproject.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
