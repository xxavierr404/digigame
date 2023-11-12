package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.UserRepository;
import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.UserCredentialsDto;
import com.xxavierr404.digigame.dto.UserRegisterDto;
import com.xxavierr404.digigame.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getByUsername(username).orElseThrow();
    }

    public void register(UserRegisterDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        repository.save(mapper.userRegisterDtoToEntity(userDto));
    }

    public Optional<User> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(User user) {
        var userSearchResult = repository.findById(user.getId());
        if (userSearchResult.isEmpty()) {
            return false;
        }
        repository.save(user);
        return true;
    }

    public boolean delete(UserCredentialsDto userCredentialsDto) {
        var userSearchResult = repository.getByUsername(userCredentialsDto.getUsername());
        if (userSearchResult.isEmpty() || !isPasswordEqual(userCredentialsDto, userSearchResult)) {
            return false;
        }
        repository.deleteById(userSearchResult.get().getId());
        return true;
    }

    private boolean isPasswordEqual(UserCredentialsDto userCredentialsDto, Optional<User> userSearchResult) {
        return userSearchResult.get().getPassword().equals(passwordEncoder.encode(userCredentialsDto.getPassword()));
    }
}
