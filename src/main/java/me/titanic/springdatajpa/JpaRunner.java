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
        // Post post = new Post();
        // post.setTitle("Spring Data Jpa 언제 보냐..");
        //
        // Comment comment = new Comment();
        // comment.setComment("빨리 보고 싶어요");
        // post.addComment(comment);
        //
        // Comment comment1 = new Comment();
        // comment1.setComment("곧 보여드릴게요");
        // post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        // session.save(post);
        Post post = session.get(Post.class, 1L);
        logger.info("==================");
        logger.info(post.getTitle());

        post.getComments().forEach(c -> {
            logger.info("==============");
            logger.info(c.getComment());
        });
    }
}
