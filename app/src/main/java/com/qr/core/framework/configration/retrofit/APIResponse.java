package com.qr.core.framework.configration.retrofit;

import androidx.annotation.Nullable;

import java.io.IOException;

import retrofit2.Response;

public class APIResponse<T> {
    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    public APIResponse(Throwable throwable) {
        code = 500;
        body = null;
        errorMessage = throwable.getMessage();
    }

    public APIResponse(Response<T> response){
        code = response.code();
        if(response.isSuccessful()){
            body = response.body();
            errorMessage = null;
        }else{
            String message = null;
            if(response.errorBody() != null){
                try {
                    message = response.errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(message == null || message.trim().length() == 0){
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
    }

    public boolean isSuccessful(){
        return code >= 200 && code < 300;
    }
}
