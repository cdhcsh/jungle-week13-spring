package org.jungle.code_post.member.dto;

import lombok.*;
import org.jungle.code_post.member.entity.Member;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String id;

    private String username;

    private String password;

    private Member.MemberRole role = Member.MemberRole.MEMBER_ROLE_USER;

    public static MemberVO of(Member member){
        return MemberVO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .role(role)
                .build();
    }
}
