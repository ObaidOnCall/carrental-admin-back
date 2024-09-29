package ma.crm.carental.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ma.crm.carental.dto.UserCreateDto;
import ma.crm.carental.dto.UserResponseDto;
import ma.crm.carental.dto.UserUpdateDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class UserService {

    @Value("${authoriztion.server.api.url}")
    private String baseUrl ;

    @Value("${authoriztion.server.token.prefix}")
    private String tokenPrefix ;
    
    private final OkHttpClient okHttpClient ;
    private final ObjectMapper objectMapper ;

    @Autowired
    public UserService (OkHttpClient okHttpClient , ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient ;
        this.objectMapper = objectMapper ;
    }
    

    public ResponseEntity<String> createUser(UserCreateDto userRequestDto , String token) throws IOException{
        String jsonBody;

        try {
            jsonBody = objectMapper.writeValueAsString(userRequestDto);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        RequestBody requestBody = RequestBody.create(
                jsonBody , 
                MediaType.get("application/json; charset=utf-8")
            ) ;
        
        Request request = new Request.Builder()
                            .url(baseUrl + "/users")
                            .post(requestBody)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization",tokenPrefix + " " + token)
                            .build() ;
        
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() != 201) {
                /**
                 * @ UserNotCreateExpetion .
                 */
                throw new IllegalArgumentException(response.body().string()) ;
            }
            return new ResponseEntity<>("User created", HttpStatus.CREATED) ;

        }
    }

    public ResponseEntity<String> updateUser (UserUpdateDto userUpdateDto , String id , String token) throws IOException {
        String jsonBody;

        try {
            jsonBody = objectMapper.writeValueAsString(userUpdateDto);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

        RequestBody requestBody = RequestBody.create(
                jsonBody , 
                MediaType.get("application/json; charset=utf-8")
            ) ;
        
        Request request = new Request.Builder()
                            .url(baseUrl + "/users/" + id)
                            .put(requestBody)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization",tokenPrefix + " " + token)
                            .build() ;
        
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() > 299) {
                /**
                 * @ UserNotUpdatedExpetion .
                 */
                throw new IllegalArgumentException(response.body().string()) ;
            }
            return new ResponseEntity<>("User updated", HttpStatus.OK) ;

        }
    }


    public ResponseEntity<String> deleteUser (String id , String token ) throws IOException {
        
        Request request = new Request.Builder()
                            .url(baseUrl + "/users/" + id)
                            .delete()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization",tokenPrefix + " " + token)
                            .build() ;
        
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() > 299) {
                /**
                 * @ UserNotUpdatedExpetion .
                 */
                throw new IllegalArgumentException(response.body().string()) ;
            }
            return new ResponseEntity<>("User deleted", HttpStatus.OK) ;

        }
    }

    public ResponseEntity<List<UserResponseDto>> showUsers (int page , int size , String token ) throws IOException {
        
        Request request = new Request.Builder()
                            .url(baseUrl + "/users?first=" + page + "&max=" +size)
                            .get()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization",tokenPrefix + " " + token)
                            .build() ;
        System.out.println(baseUrl + "/users?first=" + page + "&max=" +size + "✅");
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.code() > 299) {
                /**
                 * @ UserNotUpdatedExpetion .
                 */
                throw new IllegalArgumentException(response.body().string()) ;
            }

            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IllegalArgumentException("Response body is null");
            }

            // Parse the JSON response into a list of UserResponseDto
            String responseBodyString = responseBody.string();
            List<UserResponseDto> userResponseDtoList = objectMapper.readValue(responseBodyString, new TypeReference<List<UserResponseDto>>() {});

            return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);

        }
    }
}
