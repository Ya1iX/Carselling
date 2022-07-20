package com.practice.carselling.service.Implementation;

import com.practice.carselling.entity.User;
import com.practice.carselling.repository.UserRepository;
import com.practice.carselling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));
    }

    @Override
    public User readById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found by id: " + id));
    }

    @Override
    public List<User> list(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public List<User> readAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User save(User entity) {
        if(repository.findByUsername(entity.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already taken");
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return repository.save(entity);
    }
}
