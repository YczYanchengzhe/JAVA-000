package client;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Auther: yanchengzhe
 * @Date: 2020/10/25 23:02
 * @Description:
 */
public class HttpClientDemo {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet("http://localhost:8801");
                String responseBody = httpClient.execute(httpGet, httpResponse -> {
                    int status = httpResponse.getStatusLine().getStatusCode();
                    if (status != 200) {
                        //error
                    }
                    HttpEntity httpEntity = httpResponse.getEntity();
                    return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
                });
                System.out.println(responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
