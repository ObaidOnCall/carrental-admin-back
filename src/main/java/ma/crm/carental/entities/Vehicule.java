package ma.crm.carental.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "vehicules" ,
        indexes = {
            @Index(columnList = "tenantId" , name = "tenantId_idx")
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tenantId" , "matricule"})
        }
)
@Data  @EqualsAndHashCode(callSuper = false)
@NoArgsConstructor @AllArgsConstructor @Builder
public class Vehicule extends AbstractBaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "vhicule_seq")
    @SequenceGenerator(name = "vhicule_seq" , sequenceName = "vhicule_id_seq"  , allocationSize = 20)
    private Long id ;

    @Column(nullable = false)
    private String matricule ;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(nullable = false)
    // private Brand brand ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Model model ;

    private String color ;

    @Column(nullable = false)
    private int mileage ;

    @Column(nullable = false)
    private double price ;

    @OneToMany(mappedBy = "vehicule" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Assurance> assurances ;


    @CreationTimestamp
    private Date createdAt ;

    @UpdateTimestamp
    private Date updatedAt ;

}
