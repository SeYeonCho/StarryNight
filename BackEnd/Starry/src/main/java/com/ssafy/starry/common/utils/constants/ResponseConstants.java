package com.ssafy.starry.common.utils.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseConstants {

    public static final ResponseEntity<Void> OK =
        ResponseEntity.ok().build();

    public static final ResponseEntity<Void> CREATED =
        ResponseEntity.status(HttpStatus.CREATED).build();

    public static final ResponseEntity<Void> BAD_REQUEST =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

//    public static final ResponseEntity<Object> VALIDATION_FAILED =
//        new ResponseEntity("validation_failed", HttpStatus.BAD_REQUEST);

    public static final ResponseEntity<String> WORD_NOT_VALID =
        new ResponseEntity("Word not valid: not null && 1 <= word.length <= 10", HttpStatus.BAD_REQUEST);

    public static final ResponseEntity<String> FORBIDDEN =
        new ResponseEntity("'searchWords' is forbidden", HttpStatus.FORBIDDEN);

    public static final ResponseEntity<String> LIST_NPE =
        new ResponseEntity("API connection failed: List", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ResponseEntity<String> DATA_TREND_NPE =
        new ResponseEntity("API connection failed: DataTrend", HttpStatus.INTERNAL_SERVER_ERROR);
}
