package com.unikre.pixabay.http;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.ImageType;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import com.unikre.pixabay.params.Orientation;
import com.unikre.pixabay.params.ResponseGroup;
import lombok.Builder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class ImageSearchRequestParams extends RequestParams {
    protected static final String PARAM_RESPONSE_GROUP = "response_group";
    protected static final String PARAM_IMAGE_TYPE = "image_type";
    protected static final String PARAM_ORIENTATION = "orientation";

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

    @Override
    protected List<NameValuePair> buildHttpParams() {
        List<NameValuePair> params = super.buildHttpParams();

        if (responseGroup != null) {
            params.add(new BasicNameValuePair(PARAM_RESPONSE_GROUP, responseGroup.toString()));
        }

        if (imageType != null) {
            params.add(new BasicNameValuePair(PARAM_IMAGE_TYPE, imageType.toString()));
        }

        if (orientation != null) {
            params.add(new BasicNameValuePair(PARAM_ORIENTATION, orientation.toString()));
        }

        return params;
    }

    @Override
    protected String getURLPath() throws Exception {
        return "/";
    }

}
