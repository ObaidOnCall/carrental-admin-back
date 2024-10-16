package ma.crm.carental.dtos.vehicule;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ModelRequestDto implements ModelDtoInterface{
    
    @NotBlank
    @Size(min = 5 , max =  30)
    private String name ;
    
    private Date year ;

    @Size(min = 5 , max =  15)
    private String engineType ;

    @Size(min = 5 , max =  15)
    private String transmission ;

    @Size(min = 5 , max =  15)
    private String fuelType ;

    @Range(min = 0 , max = 3 , message = "must be between 2 and 3")
    private double length ;

    @Range(min = 0 , max = 3 , message = "must be between 2 and 3")
    private double width ;

    @Range(min = 0 , max = 3 , message = "must be between 2 and 3")
    private double height ;

    @Range(min = 0 , max = 3 , message = "must be between 2 and 3")
    private double weight ;

    @Range(min = 0 , max = 10 , message = "fuel Effeici per 100ks must be between 5 and 10")
    private double fuelEfficiency ;

    private int satingCapacity ;

    @NotNull
    @Range(min = 80 , max = 299)
    private int topSpeed ;

    @NotNull(message = "Doors number not null")
    @Range(min= 2, max = 8)
    private int numberOfDoors ;

    @NotNull(message = "The Model must have a Brand")
    private Long brand ;

}
