package com.unikre.pixabay;

import com.unikre.pixabay.params.Category;
import com.unikre.pixabay.params.ImageType;
import com.unikre.pixabay.params.Language;
import com.unikre.pixabay.params.Order;
import com.unikre.pixabay.params.Orientation;
import com.unikre.pixabay.params.ResponseGroup;
import com.unikre.pixabay.params.VideoType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayService {
    @GET("/api/")
    Call<ResponseBody> searchImages(@Query("key") String key,
                                    @Query("q") String q,
                                    @Query("lang") Language lang,
                                    @Query("id") String id,
                                    @Query("category") Category category,
                                    @Query("min_width") Integer minWidth,
                                    @Query("min_height") Integer minHeight,
                                    @Query("editors_choice") Boolean editorsChoice,
                                    @Query("safesearch") Boolean safeSearch,
                                    @Query("order") Order order,
                                    @Query("page") Integer page,
                                    @Query("per_page") Integer perPage,
                                    @Query("pretty") Boolean pretty,

                                    @Query("response_group") ResponseGroup responseGroup,
                                    @Query("image_type") ImageType imageType,
                                    @Query("orientation") Orientation orientation
    );

    @GET("/api/videos/")
    Call<ResponseBody> searchVideos(@Query("key") String key,
                                    @Query("q") String q,
                                    @Query("lang") Language lang,
                                    @Query("id") String id,
                                    @Query("category") Category category,
                                    @Query("min_width") Integer minWidth,
                                    @Query("min_height") Integer minHeight,
                                    @Query("editors_choice") Boolean editorsChoice,
                                    @Query("safesearch") Boolean safeSearch,
                                    @Query("order") Order order,
                                    @Query("page") Integer page,
                                    @Query("per_page") Integer perPage,
                                    @Query("pretty") Boolean pretty,

                                    @Query("video_type") VideoType videoType
    );
}
