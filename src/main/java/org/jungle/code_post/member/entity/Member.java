package org.jungle.code_post.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.jungle.code_post.common.entity.BaseTimestampEntity;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    private MemberRole role = MemberRole.MEMBER_ROLE_USER;

    public enum MemberRole{
        MEMBER_ROLE_ADMIN
        ,MEMBER_ROLE_USER
    }
}
