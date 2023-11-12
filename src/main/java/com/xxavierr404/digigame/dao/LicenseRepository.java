package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.License;
import com.xxavierr404.digigame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> getAllByUser(User user);
}
