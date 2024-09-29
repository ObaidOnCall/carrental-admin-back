package ma.crm.carental.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter @Getter
public class VehResponseDto {
    
    private Long id ;
    private String brand ;

    private String model ;

    private Date year ;

    private String color ;

    private int mileage ;
    
    private String fuelType ;

    private double engineSize ;

    
    private String transmissionType ;

    private int numberOfDoors ;
    
    private double weight ;

    private double topSpeed ;

    private double price ;
}
