package ma.crm.carental.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.minio.MinioAsyncClient;
import io.minio.MinioClient;
import okhttp3.OkHttpClient;

@Configuration
public class UtilsConf {
    


    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient().newBuilder()
                                 .retryOnConnectionFailure(true)
                                 .build() ;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper() ;
    }


    @Bean
    public JsonConverter jsonConverter(@Autowired ObjectMapper objectMapper) {
        return new JsonConverter(objectMapper);
    }

    @Bean
    public MinioClient minioClient () {

        return MinioClient.builder()
                .endpoint("https://storage-api.obayd.online")
                .credentials("JiNzaG97TNPaOr", "8NczT1MlNL8Vjq3o2i0pzMYVtChf")
                .build();
    }

    @Bean
    public MinioAsyncClient minioAsyncClient () {

        return MinioAsyncClient.builder()
                                .endpoint("https://storage-api.obayd.online")
                                .credentials("DcLfYpiX4FK1u", "akgSNqmV9IE8wR3pC70AX0j71Si")
                                .build();
    }
}
