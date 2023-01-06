package com.ivx.exception;


import com.ivx.util.MessageUtils;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @since 2022/12/23 17:32
 * @apiNote
*/

public class MyException extends RuntimeException{
    private final String msg;
    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }
    @Override
    public String getMessage() {
        return MessageUtils.get(msg);
    }
}
