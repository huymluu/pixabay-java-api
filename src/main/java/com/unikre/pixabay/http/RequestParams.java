package com.unikre.pixabay.http;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public abstract class RequestParams {
    @NonNull
    protected String key;
    protected String q;
    protected Language lang;
    protected String id;
    protected Category category;
    protected Integer minWidth = null;
    protected Integer minHeight = null;
    protected Boolean editorsChoice = null;
    protected Boolean safeSearch = null;
    protected Order order;
    protected Integer page = null;
    protected Integer perPage = null;
    protected Boolean pretty = null;

}
