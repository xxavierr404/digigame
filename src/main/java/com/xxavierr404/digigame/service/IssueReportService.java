package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.IssueReportRepository;
import com.xxavierr404.digigame.domain.IssueReport;
import com.xxavierr404.digigame.dto.IssueReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueReportService {
    private final IssueReportRepository repository;
    private final UserService userService;
    private final UserContentService userContentService;
    private final GameService gameService;

    public IssueReport create(IssueReportDto dto) {
        var issueReport = new IssueReport();
        issueReport.setGame(gameService.readOne(dto.getGameId()).orElseThrow());
        issueReport.setUser(userService.readOne(dto.getUserId()).orElseThrow());
        issueReport.setPost(userContentService.readOne(dto.getPostId()).orElseThrow());
        issueReport.setReportText(dto.getReportText());
        return repository.save(issueReport);
    }

    public Optional<IssueReport> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(IssueReport issueReport) {
        var searchResult = repository.findById(issueReport.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(issueReport);
        return true;
    }

    public boolean delete(Long id) {
        var searchResult = repository.findById(id);
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
