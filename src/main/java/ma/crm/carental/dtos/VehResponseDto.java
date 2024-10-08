package ma.crm.carental.dtos;


import ch.qos.logback.core.model.Model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import ma.crm.carental.entities.Brand;


@Setter @Getter
public class VehResponseDto {
    
    private Long id ;

    private String matricule ;

    private Brand brand ;

    private Model model ;

    private String color ;

    private int mileage ;

    @NotNull
    private double price ;
}
