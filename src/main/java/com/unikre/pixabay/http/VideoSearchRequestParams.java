package com.unikre.pixabay.http;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import com.unikre.pixabay.params.VideoType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoSearchRequestParams extends RequestParams {
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
}
