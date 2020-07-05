package me.titanic.springdatajpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(JpaRunner.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("JpaRunner 실행");
        Account account = new Account();
        account.setUsername("titanic");
        account.setPassword("hibernate");

        Study study = new Study();
        study.setName("Spring data jpa");

        account.addStudy(study);

        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);
    }
}
