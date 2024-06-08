package org.jungle.code_post.member.service;

import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.member.dto.MemberVO;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberserviceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public MessageResponseDTO insertMember(MemberVO memberVO) {
        if(memberRepository.existsByUsername(memberVO.getUsername()))
            return new MessageResponseDTO("duplicate username");
        Member member = memberVO.toEntity();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return new MessageResponseDTO("signup success");
    }
}
