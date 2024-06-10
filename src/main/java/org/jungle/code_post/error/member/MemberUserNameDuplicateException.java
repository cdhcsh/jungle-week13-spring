package org.jungle.code_post.error.member;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class MemberUserNameDuplicateException extends CustomGlobalException {
    public MemberUserNameDuplicateException(){
        super(HttpStatus.BAD_REQUEST,"failed","중복된 username 입니다.");
    }
}
