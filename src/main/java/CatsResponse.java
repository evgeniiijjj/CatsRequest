import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CatsResponse {

    public static void main(String[] args) {

        List<Entity> result = null;

        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build()
             ; CloseableHttpResponse response = httpClient.execute(new HttpGet(
                     "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats"))) {
            if (response.getStatusLine().getStatusCode() == 200) {
                result = new ObjectMapper().readValue(response.getEntity().getContent(), new TypeReference<List<Entity>>(){});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert result != null;

        result = result.stream()
                .filter(value -> value.upvotes != null && value.upvotes > 0)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
