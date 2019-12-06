package cz.muni.fi.pb162.hw02;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Very simple HttpClient capable of performing a GET requests and returning URL contents as a String
 * You can use the {@link OkHttpClient} instead of this simplified wrapper.
 *
 * @author Jakub Cechacek
 */
public class SimpleHttpClient {

    private OkHttpClient httpClient;

    /**
     * Creates new instance of this simple http client.
     */
    public SimpleHttpClient() {
        this(new OkHttpClient());
    }

    /**
     * Creates new instance with given {@link OkHttpClient}
     * @param httpClient underlying {@link OkHttpClient} instance
     */
    public SimpleHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * A shortcut method to get the contents of URL as String
     *
     * @param url destination URL
     * @return content of given URL as String
     * @throws IOException in case on call failure
     */
    public String get(String url) throws IOException {
        Request r = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = httpClient.newCall(r).execute()) {
            return response.body().string();
        }
    }

    /**
     * @return underlying {@link OkHttpClient} instance
     */
    public OkHttpClient getHttpClient() {
        return httpClient;
    }
}
