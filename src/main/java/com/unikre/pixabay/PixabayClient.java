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
import retrofit2.Callback;
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

    private static PixabayService pixabayService;

    private static final Gson gson = new Gson();

    private static final Type IMAGE_RESULT_TYPE = new TypeToken<Result<Image>>() {
    }.getType();

    private static final Type VIDEO_RESULT_TYPE = new TypeToken<Result<Image>>() {
    }.getType();

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

    public Result parseResponse(Response<ResponseBody> response, Type type) throws Exception {
        parseRateLimit(response);
        validateResponse(response);

        JSONObject jsonObject = new JSONObject(response.body().string());

        return gson.fromJson(jsonObject.toString(), type);
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

        return parseResponse(response, IMAGE_RESULT_TYPE);
    }

    public Result<Image> searchImage(String q) throws Exception {
        ImageSearchRequestParams params = ImageSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();

        return searchImage(params);
    }

    public void searchImage(ImageSearchRequestParams params, PixabayCallback<Result<Image>> callback) throws Exception {

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

        Callback<ResponseBody> genericCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Result<Image> result = parseResponse(response, IMAGE_RESULT_TYPE);
                    callback.onResponse(result);
                } catch (Exception e) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t);
            }
        };

        call.enqueue(genericCallback);
    }

    public void searchImage(String q, PixabayCallback<Result<Image>> callback) throws Exception {
        ImageSearchRequestParams params = ImageSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();

        searchImage(params, callback);
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

    public void searchVideo(VideoSearchRequestParams params, PixabayCallback<Result<Video>> callback) throws Exception {

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

        Callback<ResponseBody> genericCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Result<Video> result = parseResponse(response, VIDEO_RESULT_TYPE);
                    callback.onResponse(result);
                } catch (Exception e) {
                    callback.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFailure(t);
            }
        };

        call.enqueue(genericCallback);
    }

    public void searchVideo(String q, PixabayCallback<Result<Video>> callback) throws Exception {
        VideoSearchRequestParams params = VideoSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();

        searchVideo(params, callback);
    }
}
