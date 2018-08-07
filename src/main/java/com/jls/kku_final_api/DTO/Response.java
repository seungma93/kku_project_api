package com.jls.kku_final_api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private String message;
    private boolean isSuccess;
    private T response;
}
