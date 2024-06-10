package org.jungle.code_post.error.comment;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class CommentNotFoundException extends CustomGlobalException {
    public CommentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "failed", "댓글을 찾을 수 없습니다.");
    }
}
