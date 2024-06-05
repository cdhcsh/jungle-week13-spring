package org.jungle.code_post.post.service;

import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostVO;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    public PostResponseDTO insertPost(PostVO postDTO);
}
