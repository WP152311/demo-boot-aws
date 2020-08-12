package me.vvsos1.demobootaws.service.posts;

import lombok.RequiredArgsConstructor;
import me.vvsos1.demobootaws.domain.posts.Posts;
import me.vvsos1.demobootaws.domain.posts.PostsRepository;
import me.vvsos1.demobootaws.web.dto.PostsResponseDto;
import me.vvsos1.demobootaws.web.dto.PostsSaveRequestDto;
import me.vvsos1.demobootaws.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(posts);
    }
}
