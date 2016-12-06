# Intro
This is JAVA wrapper for Pixabay image/video search (https://pixabay.com/api/docs/)

Sign up for free API key (https://pixabay.com/en/accounts/register/), and have fun.

# Usage

## Initialization
```java
PixabayClient client = new PixabayClient("YOUR API KEY");
```

## Image Searching
```java
// Image search: with keyword
Result<Image> imageResponse = client.searchImage("flowers");

// Image search: with full parameter (see https://pixabay.com/api/docs/)
ImageSearchRequestParams params = ImageSearchRequestParams.builder()
                .key(apiKey)
                .imageType(ImageType.VECTOR)
                .orientation(Orientation.HORIZONTAL)
                .q("flowers")
                .build();
Result<Image> imageResponse = client.searchImage(params);

// Async request
client.searchImage("book", new PixabayCallback<Result<Image>>() {
    @Override
    public void onResponse(Result<Image> result) {
        // Handle result here
    }

    @Override
    public void onFailure(Throwable t) {
        // ...
    }
});
```

## Video Searching
```java
// Video search: with keyword
Result<Video> videoResponse = client.searchVideo("flowers");

// Video search: with full parameter (see https://pixabay.com/api/docs/)
VideoSearchRequestParams params = VideoSearchRequestParams.builder()
                .key(apiKey)
                .videoType(VideoType.FILM)
                .q("flowers")
                .build();
Result<Video> videoResponse = client.searchVideo(params);

// Async request
client.searchVideo("book", new PixabayCallback<Result<Video>>() {
    @Override
    public void onResponse(Result<Video> result) {
        // Handle result here
    }

    @Override
    public void onFailure(Throwable t) {
        // ...
    }
});
```

## Disclaimer
This repo is unofficial and is not related to pixabay.com in any way. The author is not responsible for any damage may cause by using this repo.
