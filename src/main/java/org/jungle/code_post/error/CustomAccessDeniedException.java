package org.jungle.code_post.error;

import org.springframework.http.HttpStatus;

public class CustomAccessDeniedException extends CustomGlobalException {
    public CustomAccessDeniedException(){
        super(HttpStatus.FORBIDDEN,"failed","작성자만 수정(삭제)할 수 있습니다.");

    }
}
