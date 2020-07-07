package me.titanic.springdatajpa;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;

public interface CommentRepository extends MyRepository<Comment, Long> {

    Stream<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

    Optional<Comment> findFirstByPostTitle(String postTitle);
}
