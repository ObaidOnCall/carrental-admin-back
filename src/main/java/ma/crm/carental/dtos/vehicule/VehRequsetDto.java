package ma.crm.carental.dtos.vehicule;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.Range;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ma.crm.carental.entities.Brand;
import ma.crm.carental.entities.Model;

// 🛵
@Getter @Setter
public class VehRequsetDto {

    @NotBlank
    private String matricule ;

    // @NotNull
    // private Long brand ;

    @NotNull
    private Long model ;

    private String color ;

    private int mileage ;

    private Date year ;

    @NotNull
    private double price ;
}
