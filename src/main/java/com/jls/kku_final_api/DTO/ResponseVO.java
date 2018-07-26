package com.jls.kku_final_api.DTO;

import com.jls.kku_final_api.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVO<T> {
    private int code = ResponseCode.SUCCESS;
    private String message;
    private boolean isSuccess;
    private T response;
}
