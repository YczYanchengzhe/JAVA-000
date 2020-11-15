package client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * @Auther: yanchengzhe
 * @Date: 2020/10/25 23:44
 * @Description:
 */
public class OkHttpClientDemo {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        while (true) {
            Request request = new Request.Builder().url("http://localhost:8801").build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    // ... handle failed request
                }
                String responseBody = Objects.requireNonNull(response.body()).string();
                // ... do something with response
                System.out.println(responseBody);
            } catch (IOException e) {
                // ... handle IO exception
            }
        }

    }
}
