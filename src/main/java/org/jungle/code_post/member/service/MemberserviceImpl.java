package org.jungle.code_post.member.service;

import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.common.dto.MessageResponseDTO;
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
            return new MessageResponseDTO("duplicate username");
        Member member = memberVO.toEntity();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Member.MemberRole.MEMBER_ROLE_USER);
        memberRepository.save(member);
        return new MessageResponseDTO("signup success");
    }

    @Override
    public MemberInfoDTO findMemberByUsername(MemberVO memberVO) {
        Optional<Member> findMember = memberRepository.findByUsername(memberVO.getUsername());
        if(findMember.isEmpty()) {
            log.debug("empty member");
            return null;
        }
        Member member = findMember.get();
        if(!passwordEncoder.matches(memberVO.getPassword(),member.getPassword())) {
            log.debug("incorrect password");
            return null;
        }
        log.debug(member.toString());
        return MemberInfoDTO.of(member);
    }
}
