package org.jungle.code_post.member.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.jungle.code_post.auth.jwt.JwtTokenProvider;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.member.dto.AuthLoginRequestDTO;
import org.jungle.code_post.member.dto.AuthSignupRequestDTO;
import org.jungle.code_post.member.dto.MemberInfoDTO;
import org.jungle.code_post.member.service.MemberService;
import org.jungle.code_post.member.service.MemberserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MemberController {
    @Autowired
    private MemberService memberService = new MemberserviceImpl();
    @Autowired
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @PostMapping("/signup")
    public MessageResponseDTO signup(@Validated @RequestBody AuthSignupRequestDTO requestDTO) {
        return memberService.insertMember(requestDTO.toVO());
    }

    @PostMapping("/login")
    public MessageResponseDTO login(@Validated @RequestBody AuthLoginRequestDTO requestDTO, HttpServletResponse response) {
        MemberInfoDTO member = memberService.findMemberByUsername(requestDTO.toVO());
        response.setHeader(JwtTokenProvider.AUTHORIZATION_HEADER, JwtTokenProvider.BEARER_PREFIX + jwtTokenProvider.generateToken(member));
        return new MessageResponseDTO("login success");
    }
}
