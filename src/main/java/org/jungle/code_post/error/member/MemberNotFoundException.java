package org.jungle.code_post.error.member;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends CustomGlobalException {
    public MemberNotFoundException(){
        super(HttpStatus.NOT_FOUND,"failed","회원을 찾을 수 없습니다.");
    }
}
