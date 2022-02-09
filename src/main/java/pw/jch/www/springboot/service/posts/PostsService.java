package pw.jch.www.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pw.jch.www.springboot.domain.posts.Posts;
import pw.jch.www.springboot.domain.posts.PostsRepository;
import pw.jch.www.springboot.web.dto.PostsResponseDto;
import pw.jch.www.springboot.web.dto.PostsSaveRequestDto;
import pw.jch.www.springboot.web.dto.PostsUpdateRequestDto;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 계시글이 없습니다. id = " + id));

            posts.update(requestDto.getTitle(), requestDto.getContent());
            return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 계시글이 없습니다. id =" + id));

        return new PostsResponseDto(entity);
    }
}
