package me.titanic.springdatajpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

}
