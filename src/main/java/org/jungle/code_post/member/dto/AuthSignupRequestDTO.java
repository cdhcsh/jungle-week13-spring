package org.jungle.code_post.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.jungle.code_post.post.dto.PostVO;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthSignupRequestDTO {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{4,10}$",message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$"
            ,message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다.")
    private String password;
    public MemberVO toVO(){
        return MemberVO.builder()
                .username(username)
                .password(password)
                .build();
    }
}
