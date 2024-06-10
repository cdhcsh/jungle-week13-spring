package org.jungle.code_post.member.service;

import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.error.member.MemberNotFoundException;
import org.jungle.code_post.error.member.MemberPasswordIncorrectException;
import org.jungle.code_post.error.member.MemberUserNameDuplicateException;
import org.jungle.code_post.member.dto.MemberInfoDTO;
import org.jungle.code_post.member.dto.MemberVO;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MemberserviceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public MessageResponseDTO insertMember(MemberVO memberVO) {
        if(memberRepository.existsByUsername(memberVO.getUsername()))
            throw new MemberUserNameDuplicateException();
        Member member = memberVO.toEntity();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Member.MemberRole.MEMBER_ROLE_USER);
        memberRepository.save(member);
        return new MessageResponseDTO("signup success");
    }

    @Override
    public MemberInfoDTO findMemberByUsername(MemberVO memberVO) {
        Optional<Member> findMember = memberRepository.findByUsername(memberVO.getUsername());
        Member member = findMember.orElseThrow(MemberNotFoundException::new);
        if(!passwordEncoder.matches(memberVO.getPassword(),member.getPassword()))
            throw new MemberPasswordIncorrectException();
        return MemberInfoDTO.of(member);
    }
}
