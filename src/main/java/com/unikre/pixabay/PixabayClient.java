package com.unikre.pixabay;

import com.unikre.pixabay.http.ImageSearchRequestParams;
import com.unikre.pixabay.http.VideoSearchRequestParams;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class PixabayClient {
    @Getter
    @Setter
    protected String apiKey;

    protected final CloseableHttpClient httpClient;


    public PixabayClient(String apiKey) {
        setApiKey(apiKey);

        httpClient = HttpClients.createDefault();
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

    public JSONObject searchImage(ImageSearchRequestParams params) throws Exception {
        HttpGet httpGet = params.buildHttpGet();
        CloseableHttpResponse response = httpClient.execute(httpGet);

        validateResponse(response);

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
        HttpGet httpGet = params.buildHttpGet();
        CloseableHttpResponse response = httpClient.execute(httpGet);

        validateResponse(response);

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
