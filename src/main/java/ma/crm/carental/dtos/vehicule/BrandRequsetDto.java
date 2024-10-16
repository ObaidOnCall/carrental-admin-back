package ma.crm.carental.dtos.vehicule;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrandRequsetDto {
    
    @NotBlank
    @Size(min = 3 , max = 20)
    private String name ;

    private String countryOfOrigin ;
    private String parentCompany ;
    private String website ;
}
