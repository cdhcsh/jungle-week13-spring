package org.jungle.code_post.error.post;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends CustomGlobalException {
    public PostNotFoundException(){
        super(HttpStatus.NOT_FOUND,"failed","게시물을 찾을 수 없습니다.");
    }
}
