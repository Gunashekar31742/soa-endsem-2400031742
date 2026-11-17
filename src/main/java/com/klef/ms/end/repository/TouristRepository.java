package com.klef.ms.end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.ms.end.entity.Tourist;

public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
