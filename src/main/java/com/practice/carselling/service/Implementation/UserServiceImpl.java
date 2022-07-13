package com.practice.carselling.service.Implementation;

import com.practice.carselling.entity.User;
import com.practice.carselling.repository.UserRepository;
import com.practice.carselling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));
    }

    @Override
    public List<User> readAll() {
        return repository.findAll();
    }

    @Override
    public void registerUser(User user) {
        if(repository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
