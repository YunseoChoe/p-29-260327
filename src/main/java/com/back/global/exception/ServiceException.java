package com.back.global.exception;

import com.back.global.rsData.RsData;

/* 커스텀 Exception */
public class ServiceException extends RuntimeException { // 스프링이 Execption으로 인식을 하게 하기 위해 상속 받음.

    private String msg;
    private String resultCode;

    public ServiceException(String resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public RsData<Void> getRsData() {
        return new RsData<>(msg, resultCode);
    }
}
