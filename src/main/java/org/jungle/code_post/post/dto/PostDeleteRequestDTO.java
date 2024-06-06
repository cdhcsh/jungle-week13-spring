package org.jungle.code_post.post.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDeleteRequestDTO {
    private Integer password;

    public PostVO toVO(){
        return PostVO.builder()
                .password(password)
                .build();
    }
}
