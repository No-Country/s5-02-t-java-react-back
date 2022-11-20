package com.s5.javaback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.s5.javaback.model.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
  List<Image> findByEntertainamentHomeId(Long entertainamentHomeId);
  Optional<Image> findByUserId(Long userId);
  void deleteByUserId(Long userId);
  void deleteByEntertainamentHomeId(Long entertainamentHomeId);
}
