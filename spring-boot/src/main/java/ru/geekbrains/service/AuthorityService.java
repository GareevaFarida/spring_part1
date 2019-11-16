package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persistence.AuthorityRepository;
import ru.geekbrains.persistence.entity.Authority;

import java.util.List;

@Service
@Transactional
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional(readOnly = true)
    public List<Authority> findAllWithoutUsers() {
        return authorityRepository.getAllAuthoritiesWithoutUsers();
    }

    @Transactional(readOnly = true)
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Authority getAuthorityById(Long id) {
        return authorityRepository.getById(id);
    }

    public void save(Authority authority){
        authorityRepository.save(authority);
    }
}
