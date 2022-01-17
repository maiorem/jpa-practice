package org.maiorem.jpaproject.repository;

import org.maiorem.jpaproject.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
}
