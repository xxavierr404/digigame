package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.IssueReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueReportRepository extends JpaRepository<IssueReport, Long> {
}
