package org.jungle.code_post.member.controller;

import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.member.dto.AuthSignupRequestDTO;
import org.jungle.code_post.member.service.MemberService;
import org.jungle.code_post.member.service.MemberserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MemberController {
    @Autowired
    private MemberService memberService = new MemberserviceImpl();

    @PostMapping("/signup")
    public MessageResponseDTO signup(@RequestBody AuthSignupRequestDTO requestDTO){
        return memberService.insertMember(requestDTO.toVO());
    }
}
