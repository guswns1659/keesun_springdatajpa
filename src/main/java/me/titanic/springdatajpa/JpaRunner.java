package me.titanic.springdatajpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javafx.geometry.Pos;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(JpaRunner.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) {
        List<Post> posts = entityManager.createNativeQuery("Select * from Post", Post.class)
            .getResultList();

        posts.forEach(System.out::println);
    }
}
