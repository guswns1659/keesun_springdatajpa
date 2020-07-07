package me.titanic.springdatajpa;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void crud() {
        this.createComment(100, "spring data jpa");
        this.createComment(55, "HIBERNATE SPRING");

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "LikeCount"));

        try(Stream<Comment> comments = commentRepository.findByCommentContainsIgnoreCase("Spring", pageRequest)) {
            Comment firstComment = comments.findFirst().orElse(new Comment());
            assertThat(firstComment.getLikeCount()).isEqualTo(100);
        }

        Comment comment = commentRepository.findFirstByPostTitle("postTitle").orElse(new Comment());
        assertThat(comment.getLikeCount()).isEqualTo(100);
    }

    private void createComment(int likeCount, String keyword) {
        Comment comment = new Comment();
        Post post = new Post();
        post.setTitle("postTitle");
        comment.setComment(keyword);
        comment.setLikeCount(likeCount);
        comment.setPost(post);
        commentRepository.save(comment);
    }
}

