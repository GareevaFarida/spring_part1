package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> getUserByUsername(String username);

    @Query("select distinct u " +
            "from User u " +
            "left join fetch u.authorities a " +
            "where u.id = :id")
    Optional<User> findUserByIdWithAuthorities(@Param("id") Long id);


    @Query("select distinct u " +
            "from User u " +
            "left join fetch u.authorities a " +
            "where u.username = :username")
    Optional<User> findUserByUsernameWithAuthorities(@Param("username") String username);


    @Query("select distinct u " +
            "from User u " +
            "left join fetch u.authorities a ")
    List<User> findAllUsersWithAuthorities();

    @Query("select new User(c.id, c.username) from User c")
    List<User> findAllUsersWithoutAuthorities();

    void deleteUserByUsername(String username);
}
