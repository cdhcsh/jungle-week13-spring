package org.jungle.code_post.error.member;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends CustomGlobalException {
    public MemberNotFoundException(){
        super(HttpStatus.NOT_FOUND,"failed","일치하는 사용자가 없습니다.");
    }
}
