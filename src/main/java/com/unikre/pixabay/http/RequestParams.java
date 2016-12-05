package com.unikre.pixabay.http;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public abstract class RequestParams {

    protected static final String PARAM_KEY = "key";
    protected static final String PARAM_Q = "q";
    protected static final String PARAM_LANG = "lang";
    protected static final String PARAM_ID = "id";
    protected static final String PARAM_CATEGORY = "category";
    protected static final String PARAM_MIN_WIDTH = "min_width";
    protected static final String PARAM_MIN_HEIGHT = "min_height";
    protected static final String PARAM_EDITORS_CHOICE = "editors_choice";
    protected static final String PARAM_SAFESEARCH = "safesearch";
    protected static final String PARAM_ORDER = "order";
    protected static final String PARAM_PAGE = "page";
    protected static final String PARAM_PER_PAGE = "per_page";
    protected static final String PARAM_PRETTY = "pretty";

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

    public HttpGet buildHttpGet() throws Exception {
        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("https")
                .setHost("pixabay.com")
                .setPath("/api" + getURLPath())
                .setParameters(buildHttpParams());

        return new HttpGet(uriBuilder.build());
    }

    protected abstract String getURLPath() throws Exception;

    protected List<NameValuePair> buildHttpParams() {
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        // Mandatory
        params.add(new BasicNameValuePair(PARAM_KEY, key));
        params.add(new BasicNameValuePair(PARAM_Q, q));

        // Optional
        if (lang != null) {
            params.add(new BasicNameValuePair(PARAM_LANG, lang.toString()));
        }

        if (id != null) {
            params.add(new BasicNameValuePair(PARAM_ID, id));
        }

        if (category != null) {
            params.add(new BasicNameValuePair(PARAM_CATEGORY, category.toString()));
        }

        if (minWidth != null) {
            params.add(new BasicNameValuePair(PARAM_MIN_WIDTH, minWidth.toString()));
        }

        if (minHeight != null) {
            params.add(new BasicNameValuePair(PARAM_MIN_HEIGHT, minHeight.toString()));
        }

        if (editorsChoice != null) {
            params.add(new BasicNameValuePair(PARAM_EDITORS_CHOICE, editorsChoice.toString()));
        }

        if (safeSearch != null) {
            params.add(new BasicNameValuePair(PARAM_SAFESEARCH, safeSearch.toString()));
        }

        if (order != null) {
            params.add(new BasicNameValuePair(PARAM_ORDER, order.toString()));
        }

        if (page != null) {
            params.add(new BasicNameValuePair(PARAM_PAGE, page.toString()));
        }

        if (perPage != null) {
            params.add(new BasicNameValuePair(PARAM_PER_PAGE, perPage.toString()));
        }

        if (pretty != null) {
            params.add(new BasicNameValuePair(PARAM_PRETTY, pretty.toString()));
        }

        return params;
    }

}
