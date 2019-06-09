package com.qr.core.framework.mvvm.model.repository.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T>{
    @NonNull
    public final Status         status;
    public final T              data;
    public final String message;

    private Resource(@NonNull Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data){
        return new Resource<>(Status.SUCCESS,data,null);
    }
    public static <T> Resource<T> error(String msg){
        return error(msg,null);
    }
    public static <T> Resource<T> error(String msg,@Nullable T data){
        return new Resource<>(Status.ERROR,data,msg);
    }

    public static <T> Resource<T> loading(){
        return loading(null);
    }
    public static <T> Resource<T> loading(@Nullable T data){
        return new Resource<>(Status.LOADING,data,null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (message != null ? !message.equals(resource.message) : resource.message != null) {
            return false;
        }
        return data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
