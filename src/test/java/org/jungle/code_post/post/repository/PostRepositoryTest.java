package org.jungle.code_post.post.repository;

import jakarta.transaction.Transactional;
import org.jungle.code_post.post.entity.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Post Repository 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void clean(){
        postRepository.deleteAll();
    }
    @Test
    @DisplayName("게시글 삽입 및 조회 테스트")
    public void insertPost(){

        Post insert_post = postRepository.save(Post.builder().
                title("title").
                content("content").
                link("link").
                category("category").
                score(1).
                author("동환").
                password(1234).
                build());

        Post saved_post = postRepository.findById(insert_post.getId()).get();

        assertThat(insert_post).isEqualTo(saved_post);

    }
    @Test
    @DisplayName("게시글 여러개 삽입 테스트")
    public void findPosts(){
        ArrayList<Post> insert_posts = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i++)
        {
            insert_posts.add(postRepository.save(Post.builder().
                    title("title"+i).
                    content("content").
                    link("link").
                    category("category").
                    score(1).
                    author("동환").
                    password(1234).
                    build()));
        }
        List<Post> find_posts = postRepository.findAll();
        assertThat(find_posts.size()).isEqualTo(insert_posts.size());
        for (int i = 0 ; i < 5 ; i++){
            System.out.println(find_posts.get(i));
            assertThat(find_posts.get(i)).isEqualTo(insert_posts.get(i));
        }
    }

    @Test
    @DisplayName("없는 게시글 조회 테스트")
    public void findNoExistPost(){
        Optional<Post> find_post = postRepository.findById(852798L);

        assertThatThrownBy(find_post::get).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public void updatePost(){
        String update_title = "tuple";

        Post insert_post = postRepository.save(Post.builder().
                title("title").
                content("content").
                link("link").
                category("category").
                score(1).
                author("동환").
                password(1234).
                build());

        Post find_post = postRepository.findById(insert_post.getId()).get();
        find_post.setTitle(update_title);
        postRepository.save(find_post);


        Post updated_post = postRepository.findById(insert_post.getId()).get();


        assertThat(updated_post.getTitle()).isEqualTo(update_title);



    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deletePost(){
        Post insert_post = postRepository.save(Post.builder().
                title("title").
                content("content").
                link("link").
                category("category").
                score(1).
                author("동환").
                password(1234).
                build());

        postRepository.deleteById(insert_post.getId());
        Optional<Post> delete_post = postRepository.findById(insert_post.getId());

        assertThatThrownBy(delete_post::get).isInstanceOf(NoSuchElementException.class);
    }

}
