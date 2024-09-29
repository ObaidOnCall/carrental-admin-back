package ma.crm.carental.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// 🛵
@Getter @Setter
public class VehRequsetDto {

    @NotBlank
    private String brand ;

    private String model ;

    @NotNull
    private Date year ;

    private String color ;

    private int mileage ;
    
    private String fuelType ;

    private double engineSize ;

    
    private String transmissionType ;

    @NotNull
    @Range(max = 6)
    private int numberOfDoors ;
    
    private double weight ;

    @Range(max = 300)
    private double topSpeed ;

    @NotNull
    private double price ;

    // @NotBlank(message = "every component must have a owner please send the owner .")
    // private String ownerId ;
}
