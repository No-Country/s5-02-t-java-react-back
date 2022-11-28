package com.s5.javaback.repository;

import com.s5.javaback.model.entity.HomeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeEventRepository extends JpaRepository<HomeEvent, Long> {
    List<HomeEvent> findByName(String name);
}