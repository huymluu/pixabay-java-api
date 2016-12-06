package com.unikre.pixabay.http;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.ImageType;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import com.unikre.pixabay.params.Orientation;
import com.unikre.pixabay.params.ResponseGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageSearchRequestParams extends RequestParams {
    private ResponseGroup responseGroup;
    private ImageType imageType;
    private Orientation orientation;

    @Builder
    public ImageSearchRequestParams(String key,
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

                                    ResponseGroup responseGroup,
                                    ImageType imageType,
                                    Orientation orientation) {

        super(key, q, lang, id, category, minWidth, minHeight, editorsChoice, safeSearch, order, page, perPage, pretty);

        this.responseGroup = responseGroup;
        this.imageType = imageType;
        this.orientation = orientation;
    }

}
