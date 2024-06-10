package org.jungle.code_post.auth.service;

import lombok.RequiredArgsConstructor;
import org.jungle.code_post.auth.jwt.dto.MemberDetails;
import org.jungle.code_post.error.member.MemberNotFoundException;
import org.jungle.code_post.member.dto.MemberInfoDTO;
import org.jungle.code_post.member.entity.Member;
import org.jungle.code_post.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthDetailService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);

        MemberInfoDTO dto = MemberInfoDTO.of(member);

        return new MemberDetails(dto);
    }
}
