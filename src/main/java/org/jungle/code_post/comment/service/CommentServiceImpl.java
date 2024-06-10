package org.jungle.code_post.comment.service;

import org.jungle.code_post.auth.jwt.dto.MemberDetails;
import org.jungle.code_post.comment.dto.CommentResponseDTO;
import org.jungle.code_post.comment.dto.CommentVO;
import org.jungle.code_post.comment.entity.Comment;
import org.jungle.code_post.comment.repository.CommentRepository;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.error.CustomAccessDeniedException;
import org.jungle.code_post.error.comment.CommentNotFoundException;
import org.jungle.code_post.error.post.PostNotFoundException;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentResponseDTO insertComment(CommentVO commentVO
            , MemberDetails memberDetails) {
        if (!postRepository.existsById(commentVO.getPost_id())) {
            throw new PostNotFoundException();
        }
        Comment comment = commentVO.toEntity();
        comment.setAuthor(memberDetails.getUsername());
        return CommentResponseDTO.of(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDTO updateComment(Long id, CommentVO commentVo, MemberDetails memberDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        if(!memberDetails.getRole().equals(Member.MemberRole.MEMBER_ROLE_ADMIN)
                && !comment.getAuthor().equals(memberDetails.getUsername()))
            throw new CustomAccessDeniedException();
        comment.setContent(commentVo.getContent());
        return CommentResponseDTO.of(commentRepository.save(comment));
    }

    @Override
    public MessageResponseDTO deleteComment(Long id, MemberDetails memberDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        if(!memberDetails.getRole().equals(Member.MemberRole.MEMBER_ROLE_ADMIN)
                && !comment.getAuthor().equals(memberDetails.getUsername()))
            throw new CustomAccessDeniedException();
        commentRepository.delete(comment);
        return MessageResponseDTO.builder().message("댓글 삭제를 성공했습니다.").build();
    }
}
