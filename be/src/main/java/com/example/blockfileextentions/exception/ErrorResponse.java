package com.example.blockfileextentions.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 * packageName    : com.example.blockfileextentions.exception
 * fileName       : ErrorResponse
 * author         : yyh77
 * date           : 2023-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-24        yyh77       최초 생성
 */
@Getter
@Builder
public class ErrorResponse {

    private final int status;

    private String error;

    private String code;

    private String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus().value())
                        .error(errorCode.getStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
