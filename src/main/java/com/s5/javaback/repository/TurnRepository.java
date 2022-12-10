package com.s5.javaback.repository;

import com.s5.javaback.model.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
    List<Turn> findByDays(LocalDate days);
}
