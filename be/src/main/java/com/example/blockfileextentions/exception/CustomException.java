package com.example.blockfileextentions.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.example.blockfileextentions.exception
 * fileName       : CustomException
 * author         : yyh77
 * date           : 2023-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-24        yyh77       최초 생성
 */
@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
}
