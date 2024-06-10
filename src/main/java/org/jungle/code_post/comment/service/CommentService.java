package org.jungle.code_post.comment.service;

import org.jungle.code_post.auth.jwt.dto.MemberDetails;
import org.jungle.code_post.comment.dto.CommentResponseDTO;
import org.jungle.code_post.comment.dto.CommentVO;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentResponseDTO insertComment(CommentVO commentVO, MemberDetails memberDetails);

    public CommentResponseDTO updateComment(Long id,CommentVO commentVo,MemberDetails memberDetails);

    public MessageResponseDTO deleteComment(Long id,MemberDetails memberDetails);
}
