package org.jungle.code_post.error.member;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class MemberPasswordIncorrectException extends CustomGlobalException {
    public MemberPasswordIncorrectException(){
        super(HttpStatus.BAD_REQUEST,"failed","비밀번호가 일치하지 않습니다.");
    }
}
