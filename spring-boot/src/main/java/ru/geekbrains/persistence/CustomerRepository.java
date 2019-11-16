package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.persistence.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select new Customer (c.id,c.name,c.contacts) from Customer c")
    List<Customer> findAll();

    Optional<Customer> findById(Long id);

}
