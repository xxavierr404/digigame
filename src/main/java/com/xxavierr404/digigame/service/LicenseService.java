package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.LicenseRepository;
import com.xxavierr404.digigame.domain.License;
import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.LicenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseService {
    private final LicenseRepository repository;
    private final GameService gameService;
    private final UserService userService;

    public License create(LicenseDto licenseDto) {
        var license = new License();
        license.setPurchaseTime(license.getPurchaseTime());
        license.setGame(gameService.readOne(licenseDto.getGameId()).orElseThrow());
        license.setUser(userService.readOne(licenseDto.getUserId()).orElseThrow());
        return repository.save(license);
    }

    public Optional<License> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(License license) {
        var searchResult = repository.findById(license.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(license);
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

    public List<License> findByUser(User user) {
        return repository.getAllByUser(user);
    }
}
