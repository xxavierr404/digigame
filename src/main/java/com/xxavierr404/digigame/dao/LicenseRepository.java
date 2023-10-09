package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
}
