package me.vvsos1.demobootaws.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository repository;

    @After
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void post_save_load() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        repository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("vvsos1@hotmail.co.kr")
                .build());

        //when
        List<Posts> postsList = repository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}