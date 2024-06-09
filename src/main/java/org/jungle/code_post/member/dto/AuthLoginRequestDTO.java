package org.jungle.code_post.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequestDTO {
    private String username;
    private String password;
    public MemberVO toVO(){
        return MemberVO.builder()
                .username(username)
                .password(password)
                .build();
    }
}
