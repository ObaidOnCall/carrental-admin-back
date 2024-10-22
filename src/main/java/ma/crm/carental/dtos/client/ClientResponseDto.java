package ma.crm.carental.dtos.client;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ClientResponseDto {
    
    private long id ;

    private String firstname ;

    private String lastname ;

    private String cinOrPassport ;

    private String licence ;

    private String nationality ;

    private String address ;

    private String ville ;

    private int codePostal ;

    private String phone1 ;

    private String phone2 ;

    private String email ;

    private Date cinIsValideUntil ;

    
    private Date licenceIsValideUntil ;

    private String clientType ;
    
}
