package me.titanic.springdatajpa;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @DisplayName("PostRepository의 save를 테스트")
    @Test
    @Rollback(false)
    public void crudRepository() {
        // Given
        Post post = new Post();
        post.setTitle("hello spring boot common");
        assertThat(post.getId()).isNull();

        // When
        Post newPost = postRepository.save(post);

        // Then
        assertThat(newPost.getId()).isNotNull();

        // When
        List<Post> posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts).contains(newPost);

        // When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));

        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        page = postRepository.findByTitleContains("spring", PageRequest.of(0, 10));

        // Then
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getSize()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(1);

        // When
        long count = postRepository.countByTitleContains("spring");

        // Then
        assertThat(count).isEqualTo(1);
    }
}
