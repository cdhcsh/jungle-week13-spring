package org.jungle.code_post.error.post;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class PostAccessDeniedException extends CustomGlobalException {
    public PostAccessDeniedException(){
        super(HttpStatus.FORBIDDEN,"failed","작성자만 수정(삭제)할 수 있습니다.");

    }
}
