package org.jungle.code_post.member.dto;

import lombok.*;
import org.jungle.code_post.member.entity.Member;

@Builder
@Data
public class MemberInfoDTO {
    private final String username;
    private final Member.MemberRole role;
    public static MemberInfoDTO of(Member member){
        return MemberInfoDTO.builder()
                .username(member.getUsername())
                .role(member.getRole())
                .build();
    }
}
