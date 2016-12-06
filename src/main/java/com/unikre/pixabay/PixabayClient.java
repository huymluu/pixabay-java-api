package com.unikre.pixabay;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unikre.pixabay.http.Image;
import com.unikre.pixabay.http.ImageSearchRequestParams;
import com.unikre.pixabay.http.Result;
import com.unikre.pixabay.http.Video;
import com.unikre.pixabay.http.VideoSearchRequestParams;
import lombok.Getter;
import lombok.Setter;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.lang.reflect.Type;

public class PixabayClient {
    public static final int REQUEST_LIMIT_PER_HOUR = 5000;

    @Getter
    @Setter
    protected String apiKey;

    @Getter
    protected int requestsLimitIn30min;

    @Getter
    protected int remainingRequests;

    @Getter
    protected int remainingSecsToResetLimit;

    private PixabayService pixabayService;

    private final Gson gson = new Gson();

    public PixabayClient(String apiKey) {
        setApiKey(apiKey);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .build();
        pixabayService = retrofit.create(PixabayService.class);
    }

    private void parseRateLimit(Response response) {
        String value = response.headers().get("X-RateLimit-Limit");
        if (value != null && value.length() > 0) {
            requestsLimitIn30min = Integer.parseInt(value);
        }

        value = response.headers().get("X-RateLimit-Remaining");
        if (value != null && value.length() > 0) {
            remainingRequests = Integer.parseInt(value);
        }

        value = response.headers().get("X-RateLimit-Reset");
        if (value != null && value.length() > 0) {
            remainingSecsToResetLimit = Integer.parseInt(value);
        }

    }

    private void validateResponse(Response response) throws Exception {
        // Result - status code
        int statusCode = response.code();
        if (statusCode != 200) {
            throw new Exception("API call error: " + statusCode + " - " + response.message());
        }

        // Result - body
        if (response.body() == null) {
            throw new Exception("API call error: Empty response body");
        }
    }

    /**
     * Image search
     **/
    public Result<Image> searchImage(ImageSearchRequestParams params) throws Exception {

        Call<ResponseBody> call = pixabayService.searchImages(params.getKey(),
                params.getQ(),
                params.getLang(),
                params.getId(),
                params.getCategory(),
                params.getMinWidth(),
                params.getMinHeight(),
                params.getEditorsChoice(),
                params.getSafeSearch(),
                params.getOrder(),
                params.getPage(),
                params.getPerPage(),
                params.getPretty(),

                params.getResponseGroup(),
                params.getImageType(),
                params.getOrientation());

        Response<ResponseBody> response = call.execute();

        parseRateLimit(response);
        validateResponse(response);

        JSONObject jsonObject = new JSONObject(response.body().string());

        Type collectionType = new TypeToken<Result<Image>>() {
        }.getType();
        return gson.fromJson(jsonObject.toString(), collectionType);
    }

    public Result<Image> searchImage(String q) throws Exception {
        ImageSearchRequestParams params = ImageSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();

        return searchImage(params);
    }

    /**
     * Video search
     **/
    public Result<Video> searchVideo(VideoSearchRequestParams params) throws Exception {
        Call<ResponseBody> call = pixabayService.searchVideos(params.getKey(),
                params.getQ(),
                params.getLang(),
                params.getId(),
                params.getCategory(),
                params.getMinWidth(),
                params.getMinHeight(),
                params.getEditorsChoice(),
                params.getSafeSearch(),
                params.getOrder(),
                params.getPage(),
                params.getPerPage(),
                params.getPretty(),

                params.getVideoType());

        Response<ResponseBody> response = call.execute();

        JSONObject jsonObject = new JSONObject(response.body().string());

        Type collectionType = new TypeToken<Result<Video>>() {
        }.getType();
        return gson.fromJson(jsonObject.toString(), collectionType);
    }

    public Result<Video> searchVideo(String q) throws Exception {
        VideoSearchRequestParams params = VideoSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();
        return searchVideo(params);
    }
}
