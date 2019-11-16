package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.error.ResourceNotFoundException;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persistence.UserRepository;
import ru.geekbrains.persistence.entity.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserRepr userRepr) {
        User user = new User();
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        userRepository.save(user);
    }

    public void update(User user) {
        User user1 = userRepository.getUserByUsername(user.getUsername()).orElseThrow(()->new ResourceNotFoundException());
        user1.setId(user.getId());
        user1.setPassword(user.getPassword());
        userRepository.delete(user);
        userRepository.save(user1);
    }

    public Optional<User> getUserByIdWithAuthorities(Long id){
        return userRepository.findUserByIdWithAuthorities(id);
    }
    public void deleteUser(UserRepr userRepr) {
        userRepository.deleteUserByUsername(userRepr.getUsername());
    }

    //@org.springframework.transaction.annotation.Transactional(readOnly = true)
    public User findUserByIdWithAuthorities(Long id) {
        return userRepository.findUserByIdWithAuthorities(id).orElseThrow(()-> new ResourceNotFoundException());
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<User> findAllUsersWithAuthorities() {
        return userRepository.findAllUsersWithAuthorities();
    }

    public List<User> findAllUsersWithoutAuthorities() {
        return userRepository.findAllUsersWithoutAuthorities();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
