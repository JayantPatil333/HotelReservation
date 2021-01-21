package com.reservation.response;

import com.reservation.exception.ApiError;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponseImpl<T> {
    private HttpStatus status;
    private ApiError apiError;
    private T actualResponse;

    /*public ApiResponseImpl(HttpStatus status, ApiError apiError, T actualResponse) {
        this.status = status;
        this.apiError = apiError;
        this.actualResponse = actualResponse;
    }*/
}
