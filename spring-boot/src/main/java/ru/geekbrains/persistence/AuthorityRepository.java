package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persistence.entity.Authority;
import ru.geekbrains.persistence.entity.Category;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("select new Authority(c.id, c.name) from Authority c")
    List<Authority> getAllAuthoritiesWithoutUsers();


    Authority getById(Long id);
}
