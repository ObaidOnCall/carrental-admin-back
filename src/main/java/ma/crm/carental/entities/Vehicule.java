package ma.crm.carental.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "vehicules" ,
        indexes = {
            @Index(columnList = "ownerId" , name = "ownerId_idx")
        }
    )
@Data @NoArgsConstructor @AllArgsConstructor
public class Vehicule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "vhicule_seq")
    @SequenceGenerator(name = "vhicule_seq" , sequenceName = "vhicule_id_seq"  , allocationSize = 20)
    private Long id ;

    private String brand ;

    private String model ;

    /**
     * @see The year the vehicle was manufactured.
     */

    private Date year ;


    private String color ;

    @Column(nullable = false)
    private int mileage ;

    /**
     * @see must be Enume
     */

    private String fuelType ;

    @Column(nullable = false)
    private double engineSize ;


    private String transmissionType ;

    @Column(nullable = false)
    private int numberOfDoors ;


    private String licensePlate ;


    private double weight ;

    @Column(nullable = false)
    private double topSpeed ;

    @Column(nullable = false)
    private double price ;

    @Column(nullable = false , updatable = false , length = 64)
    private String ownerId ;



}
