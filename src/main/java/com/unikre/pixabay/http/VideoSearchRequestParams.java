package com.unikre.pixabay.http;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import com.unikre.pixabay.params.VideoType;
import lombok.Builder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class VideoSearchRequestParams extends RequestParams {

    protected static final String PARAM_VIDEO_TYPE = "video_type";

    private VideoType videoType;

    @Builder
    public VideoSearchRequestParams(String key,
                                    String q,
                                    Language lang,
                                    String id,
                                    Category category,
                                    Integer minWidth,
                                    Integer minHeight,
                                    Boolean editorsChoice,
                                    Boolean safeSearch,
                                    Order order,
                                    Integer page,
                                    Integer perPage,
                                    Boolean pretty,

                                    VideoType videoType) {

        super(key, q, lang, id, category, minWidth, minHeight, editorsChoice, safeSearch, order, page, perPage, pretty);

        this.videoType = videoType;
    }

    @Override
    protected List<NameValuePair> buildHttpParams() {
        List<NameValuePair> params = super.buildHttpParams();

        if (videoType != null) {
            params.add(new BasicNameValuePair(PARAM_VIDEO_TYPE, videoType.toString()));
        }

        return params;
    }

    @Override
    protected String getURLPath() throws Exception {
        return "/videos";
    }

}
