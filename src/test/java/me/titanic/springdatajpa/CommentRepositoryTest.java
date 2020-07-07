package me.titanic.springdatajpa;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() throws  InterruptedException {
        this.createComment(100, "spring data jpa");
        this.createComment(55, "HIBERNATE SPRING");
        commentRepository.flush();

        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));

        ListenableFuture<List<Comment>> future =
            commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest);
        System.out.println("=================");
        System.out.println("is done? " + future.isDone());

        future.addCallback(new ListenableFutureCallback<List<Comment>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            @Override
            public void onSuccess(@Nullable List<Comment> comments) {
                System.out.println("=== Async ===");
                System.out.println(comments.size());
            }
        });

        Thread.sleep(5000L);
    }

    private void createComment(int likeCount, String keyword) {
        Comment comment = new Comment();
        comment.setComment(keyword);
        comment.setLikeCount(likeCount);
        commentRepository.save(comment);
    }
}

