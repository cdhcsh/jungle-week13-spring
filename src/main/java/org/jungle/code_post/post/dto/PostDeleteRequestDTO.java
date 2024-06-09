package org.jungle.code_post.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDeleteRequestDTO {
    @NotNull
    private Integer password;

    public PostVO toVO(){
        return PostVO.builder()
                .password(password)
                .build();
    }
}
