package ma.crm.carental.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.SnowballObject;
import io.minio.UploadSnowballObjectsArgs;
import io.minio.errors.MinioException;
import ma.crm.carental.annotations.ValidateClients;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.dtos.docs.MetaData;




@Service
@Transactional
public class ClientObjectsService {
    
	private static final String ERRORMESSAGE = "access denied or unable to process the item within the client" ;

    private final MinioClient minioClient ;
    private final ClientService clientService ;

    @Value("${object.storage.bucket.name.client}")
    private  String bucketNameForClient ;
    
    @Autowired
    ClientObjectsService(
        MinioClient minioClient ,
        ClientService clientService
        ) {
        this.minioClient = minioClient ;
        this.clientService = clientService ;
    }

    @ValidateClients
    public ObjectWriteResponse upload (
        List<MetaData> docsMetaData ,
        List<MultipartFile> files
    ) throws MinioException, IOException, InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException{

        /**
         * by getting the client we verify that the org own this client
         */
        ClientResponseDto client = clientService.findClient(docsMetaData.get(0).getClient()) ;


        List<SnowballObject> snowballObjects = new ArrayList<>();

        // Convert each MultipartFile to a SnowballObject and add to the list
        for (MultipartFile file : files) {
            SnowballObject snowballObject = new SnowballObject(
                client.getFirstname()+ "__" +file.getOriginalFilename(),
                new ByteArrayInputStream(file.getBytes()),
                file.getSize(),
                null // Optionally, you can provide compression methods here
            );
            snowballObjects.add(snowballObject);
        }

        // Upload the files as a single TAR package
        return minioClient.uploadSnowballObjects(
                    UploadSnowballObjectsArgs.builder()
                        .bucket("clients")
                        .objects(snowballObjects)
                        .build()
            );
    }
    

    

}
