package com.ivx;


import com.ivx.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @since 2022/12/23 17:22
 * @apiNote
*/
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    public String securityExceptionHandler(MyException e) {
        return e.getMessage();
    }
}
