package org.jungle.code_post.post.service;

import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.auth.jwt.dto.MemberDetails;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.error.post.PostAccessDeniedException;
import org.jungle.code_post.error.post.PostNotFoundException;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostVO;
import org.jungle.code_post.post.entity.Post;
import org.jungle.code_post.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

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
    public PostResponseDTO insertPost(PostVO postVO, MemberDetails memberDetails) {
        Post post = postVO.toEntity();
        post.setAuthor(memberDetails.getUsername());
        return PostResponseDTO.of(postRepository.save(post));
    }

    @Override
    public PostResponseDTO updatePost(Long id, PostVO postVO, MemberDetails memberDetails) {
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.orElseThrow(PostNotFoundException::new);
        if(!memberDetails.getRole().equals(Member.MemberRole.MEMBER_ROLE_ADMIN)
                && !post.getAuthor().equals(memberDetails.getUsername()))
            throw new PostAccessDeniedException();

        post.setTitle(postVO.getTitle());
        post.setContent(postVO.getContent());
        post.setLink(postVO.getLink());
        post.setCategory(postVO.getCategory());
        post.setScore(postVO.getScore());

        return PostResponseDTO.of(postRepository.save(post));
    }

    @Override
    public MessageResponseDTO deletePost(Long id,MemberDetails memberDetails) {
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.orElseThrow(PostNotFoundException::new);
        if(!memberDetails.getRole().equals(Member.MemberRole.MEMBER_ROLE_ADMIN)
                && !post.getAuthor().equals(memberDetails.getUsername()))
            throw new PostAccessDeniedException();
        postRepository.delete(post);
        return new MessageResponseDTO("게시물 삭제가 완료되었습니다.");
    }
}
