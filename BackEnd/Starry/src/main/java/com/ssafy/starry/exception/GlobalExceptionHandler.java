package com.ssafy.starry.exception;

import static com.ssafy.starry.common.utils.constants.ResponseConstants.DATA_TREND_NPE;
import static com.ssafy.starry.common.utils.constants.ResponseConstants.FORBIDDEN;
import static com.ssafy.starry.common.utils.constants.ResponseConstants.LIST_NPE;
import static com.ssafy.starry.common.utils.constants.ResponseConstants.WORD_NOT_VALID;

import com.ssafy.starry.common.utils.notification.NotificationManager;
import com.ssafy.starry.exception.externalApi.DataTrendNullPointerException;
import com.ssafy.starry.exception.externalApi.ListNullPointerException;
import com.ssafy.starry.exception.valid.ForbiddenWordException;
import com.ssafy.starry.exception.valid.WordNotValidException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final NotificationManager notificationManager;

    /*
    금지된 단어가 입력되었을 때 발생하는 Exception _ searchWords
     */
    @ExceptionHandler(ForbiddenWordException.class)
    public ResponseEntity<String> handleForbiddenWordException(
        ForbiddenWordException ex, HttpServletRequest req
    ) {
        log.debug("Forbidden word: searchWords", ex);
        notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
        return FORBIDDEN;
    }

    /*
    검색어가 null이거나 길이가 10 이상인 단어를 검색한 경우 발생하는 Exception
     */
    @ExceptionHandler(WordNotValidException.class)
    public ResponseEntity<String> handleForbiddenWordException(
        WordNotValidException ex, HttpServletRequest req
    ) {
        log.debug("Word not valid: not null && 1 <= word.length <= 10", ex);
        notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
        return WORD_NOT_VALID;
    }

    /*
    WordService _ getDataTrend method가 null을 반환할 때 발생하는 Exception
     */
    @ExceptionHandler(DataTrendNullPointerException.class)
    public ResponseEntity<String> handleDataTrendNullPointerException(
        DataTrendNullPointerException ex, HttpServletRequest req
    ) {
        log.debug("API connection failed: LIST_NPE", ex);
        notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
        return DATA_TREND_NPE;
    }

    /*
    WordService _ list method가 null을 반환할 때 발생하는 Exception
     */
    @ExceptionHandler(ListNullPointerException.class)
    public ResponseEntity<String> handleListNullPointerException(
        ListNullPointerException ex, HttpServletRequest req
    ) {
        log.debug("API connection failed: DATA_TREND_NPE", ex);
        notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
        return LIST_NPE;
    }

    // 5xx error handler : 서버에서 발생한 전반적인 에러에 대한 핸들러
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request,
        HttpServletRequest req) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .Error(request.getDescription(false))
            .message(ex.getMessage())
            .build();
        log.debug("server error", ex);
        notificationManager.sendNotification(ex, req.getRequestURI(), getParams(req));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getParams(HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key))
                .append('\n');
        }

        return params.toString();
    }
}
