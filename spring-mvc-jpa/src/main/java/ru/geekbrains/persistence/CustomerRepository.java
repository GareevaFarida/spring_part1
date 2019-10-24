package ru.geekbrains.persistence;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.persistence.entity.Customer;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CustomerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Customer customer) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(customer);

        em.getTransaction().commit();
        em.close();
    }

    public void update(Customer customer) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        em.merge(customer);

        em.getTransaction().commit();
        em.close();
    }

    public List<Customer> findAll() {
        EntityManager em = sessionFactory.createEntityManager();

//        List<Category> categories = em.createQuery("from Category", Category.class).getResultList();
        List<Customer> customers = em.createQuery(new String("from Customer"), Customer.class).getResultList();
        em.close();
        return customers;
    }

    public Customer findById(Long id) {
        EntityManager em = sessionFactory.createEntityManager();

        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;
    }
}
