package com.example.handschriftenportal.repository;

import com.example.handschriftenportal.entity.Manuscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManuscriptRepository extends JpaRepository<Manuscript, Long> {
}
