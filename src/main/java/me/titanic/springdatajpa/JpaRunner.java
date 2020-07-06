package me.titanic.springdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(JpaRunner.class);

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) {
        Post post = new Post();
        post.setTitle("spring");

        Comment comment = new Comment();
        comment.setComment("hello");

        post.addComment(comment);

        postRepository.save(post);
    }
}
