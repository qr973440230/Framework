package com.qr.core.framework.configration.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NetworkResponse<T>{
    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    private NetworkResponse(int code, @Nullable T body, @Nullable String errorMessage) {
        this.code = code;
        this.body = body;
        this.errorMessage = errorMessage;
    }

    public static <T> NetworkResponse<T> success(@Nullable T body){
        return new NetworkResponse<>(200,body,null);
    }
    public static <T> NetworkResponse<T> error(@Nullable String errorMessage){
        return new NetworkResponse<>(500,null,errorMessage);
    }
    public static <T> NetworkResponse<T> error(Throwable throwable){
        return new NetworkResponse<>(500,null,throwable.getMessage());
    }

    public boolean isSuccessful(){
        return code == 200;
    }
}
