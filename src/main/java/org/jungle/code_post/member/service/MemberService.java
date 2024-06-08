package org.jungle.code_post.member.service;

import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.member.dto.MemberVO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    public MessageResponseDTO insertMember(MemberVO memberVO);
}
