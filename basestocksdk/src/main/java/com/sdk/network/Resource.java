package com.sdk.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T extends BaseResponse> {
    @NonNull
    private final ResourceStatus tsStatus;
    @Nullable
    private final T response;

    private final ErrorModel errorModel;

    public ErrorModel getErrorModel() {
        return errorModel;
    }

    @Nullable
    public T getResponse() {
        return response;
    }

    @NonNull
    public ResourceStatus getTsStatus() {
        return tsStatus;
    }

    public Resource(@NonNull ResourceStatus tsStatus, @Nullable T response) {
        this(tsStatus, null, response);
    }

    public Resource(@NonNull ResourceStatus tsStatus, ErrorModel errorModel, @Nullable T response) {
        this.tsStatus = tsStatus;
        this.response = response;
        this.errorModel = errorModel;
    }

    public static <T extends BaseResponse> Resource<T> success(@NonNull T data) {
        return new Resource<>(ResourceStatus.SUCCESS, data);
    }

    public static <T extends BaseResponse> Resource<T> error(ErrorModel errorModel, @Nullable T data) {
        return new Resource<>(ResourceStatus.ERROR, errorModel, data);
    }


    public static <T extends BaseResponse> Resource<T> loading(@Nullable T data) {
        return new Resource<>(ResourceStatus.LOADING, data);
    }

    public boolean isSuccess() {
        return (tsStatus == ResourceStatus.SUCCESS) && response != null && response.isSuccess();
    }
}
