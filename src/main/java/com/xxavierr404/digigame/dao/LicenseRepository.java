package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    @Query(value = "SELECT * FROM license WHERE user_id = :userId", nativeQuery = true)
    List<License> getAllByUserId(@Param("userId") Long userId);
}
