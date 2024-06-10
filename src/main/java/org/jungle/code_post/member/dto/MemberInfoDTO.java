package org.jungle.code_post.member.dto;

import lombok.*;
import org.jungle.code_post.member.entity.Member;

@Builder
@Data
public class MemberInfoDTO {
    private final String id;
    private final String username;
    private final String password;
    private final Member.MemberRole role;
    public static MemberInfoDTO of(Member member){
        return MemberInfoDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
    }
}
