package org.jungle.code_post.member.dto;

import lombok.*;
import org.jungle.code_post.post.dto.PostVO;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthSignupRequestDTO {
    private String username;
    private String password;
    public MemberVO toVO(){
        return MemberVO.builder()
                .username(username)
                .password(password)
                .build();
    }
}
