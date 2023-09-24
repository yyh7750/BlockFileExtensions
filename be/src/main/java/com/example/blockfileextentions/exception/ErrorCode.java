package com.example.blockfileextentions.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.example.blockfileextentions.exception
 * fileName       : ErrorCode
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
public enum ErrorCode {

    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "해당 확장자는 이미 차단되어 있습니다. 차단 해제되었습니다."),
    OVER_LENGTH(HttpStatus.BAD_REQUEST, "차단된 확장자 개수가 최대입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
