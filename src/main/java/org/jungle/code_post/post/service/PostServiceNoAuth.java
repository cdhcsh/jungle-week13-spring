package org.jungle.code_post.post.service;

import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.error.post.PostNotFoundException;
import org.jungle.code_post.error.post.PostPasswordIncorrectException;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostVO;
import org.jungle.code_post.post.entity.Post;
import org.jungle.code_post.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceNoAuth implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostResponseDTO findPostById(Long id) {
        Optional<Post> findPost = postRepository.findById(id);
        return PostResponseDTO.of(findPost.orElseThrow(PostNotFoundException::new));
    }

    @Override
    public List<PostResponseDTO> findAllPost() {
        return postRepository.findAll().stream()
                .map(PostResponseDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO insertPost(PostVO postVO) {
        return PostResponseDTO.of(postRepository.save(postVO.toEntity()));
    }

    @Override
    public PostResponseDTO updatePost(Long id, PostVO postVO) {
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.orElseThrow(PostNotFoundException::new);

        if (post.getPassword() != postVO.getPassword())
            throw new PostPasswordIncorrectException();

        post.setTitle(postVO.getTitle());
        post.setContent(postVO.getContent());
        post.setLink(postVO.getLink());
        post.setCategory(postVO.getCategory());
        post.setScore(postVO.getScore());

        return PostResponseDTO.of(postRepository.save(post));
    }

    @Override
    public MessageResponseDTO deletePost(Long id,PostVO postVO) {
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.orElseThrow(PostNotFoundException::new);
        if (post.getPassword() != postVO.getPassword())
            throw new PostPasswordIncorrectException();
        postRepository.delete(post);
        return new MessageResponseDTO("delete success!");
    }
}
