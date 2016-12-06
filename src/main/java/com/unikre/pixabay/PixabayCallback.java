package com.unikre.pixabay;

public interface PixabayCallback<T> {
    void onResponse(T result);

    void onFailure(Throwable t);
}
