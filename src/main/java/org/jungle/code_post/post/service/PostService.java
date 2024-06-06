package org.jungle.code_post.post.service;

import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public PostResponseDTO findPostById(Long id);

    public List<PostResponseDTO> findAllPost();

    public PostResponseDTO insertPost(PostVO postVo);

    public PostResponseDTO updatePost(Long id,PostVO postVO);
}
