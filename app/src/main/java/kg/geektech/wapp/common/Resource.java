package kg.geektech.wapp.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final kg.geektech.wapp.common.Status status;
    public final T data;
    public final String msg;

    public Resource(@NonNull kg.geektech.wapp.common.Status status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(kg.geektech.wapp.common.Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(kg.geektech.wapp.common.Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(kg.geektech.wapp.common.Status.LOADING, null, null);
    }
}

