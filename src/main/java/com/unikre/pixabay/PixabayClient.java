package com.unikre.pixabay;

import com.unikre.pixabay.http.ImageSearchRequestParams;
import com.unikre.pixabay.http.RequestParams;
import com.unikre.pixabay.http.VideoSearchRequestParams;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class PixabayClient {
    public static final int REQUEST_LIMIT_PER_HOUR = 5000;

    @Getter
    @Setter
    protected String apiKey;

    @Getter
    @Setter
    protected int requestsLimitIn30min;

    @Getter
    @Setter
    protected int remainingRequests;

    @Getter
    @Setter
    protected int remainingSecsToResetLimit;

    protected final CloseableHttpClient httpClient;


    public PixabayClient(String apiKey) {
        setApiKey(apiKey);

        httpClient = HttpClients.createDefault();
    }

    private void parseRateLimit(CloseableHttpResponse response) {
        Header header = response.getFirstHeader("X-RateLimit-Limit");
        if (header != null) {
            String value = header.getValue();
            if (value != null && value.length() > 0)
                requestsLimitIn30min = Integer.parseInt(value);
        }

        header = response.getFirstHeader("X-RateLimit-Remaining");
        if (header != null) {
            String value = header.getValue();
            if (value != null && value.length() > 0)
                remainingRequests = Integer.parseInt(value);
        }

        header = response.getFirstHeader("X-RateLimit-Reset");
        if (header != null) {
            String value = header.getValue();
            if (value != null && value.length() > 0)
                remainingSecsToResetLimit = Integer.parseInt(value);
        }

    }

    private void validateResponse(CloseableHttpResponse response) throws Exception {
        // Response - status code
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new Exception("API call error: " + statusCode + " - " + response.getStatusLine().getReasonPhrase());
        }

        // Response - body
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity == null) {
            throw new Exception("API call error: Empty response body");
        }
    }

    private CloseableHttpResponse request(RequestParams requestParams) throws Exception {
        HttpGet httpGet = requestParams.buildHttpGet();

        CloseableHttpResponse response = httpClient.execute(httpGet);

        parseRateLimit(response);
        validateResponse(response);

        return response;
    }

    public JSONObject searchImage(ImageSearchRequestParams params) throws Exception {
        CloseableHttpResponse response = request(params);
        return new JSONObject(EntityUtils.toString(response.getEntity()));
    }

    public JSONObject searchImage(String q) throws Exception {
        ImageSearchRequestParams params = ImageSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();

        return searchImage(params);
    }

    public JSONObject searchVideo(VideoSearchRequestParams params) throws Exception {
        CloseableHttpResponse response = request(params);
        return new JSONObject(EntityUtils.toString(response.getEntity()));
    }

    public JSONObject searchVideo(String q) throws Exception {
        VideoSearchRequestParams params = VideoSearchRequestParams.builder()
                .key(apiKey)
                .q(q)
                .build();
        return searchVideo(params);
    }
}
