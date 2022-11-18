package com.s5.javaback.repository;

import com.s5.javaback.model.entity.HomeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntertainamentHomeRepository extends JpaRepository<HomeEvent, Long> {
}