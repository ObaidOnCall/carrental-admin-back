package ma.crm.carental.entities;

import java.util.List;

import org.hibernate.annotations.TenantId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(
    name = "brands" ,
    indexes = {
        @Index(name = "tenantId_idx", columnList = "tenant_id") 
    }
)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Brand extends AbstractBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "brand_seq")
    @SequenceGenerator(name = "brand_seq" , sequenceName = "brand_id_seq"  , allocationSize = 1)
    private Long id ;

    @Column(nullable = false)
    private String name ;
    private String countryOfOrigin ;
    private String parentCompany ;
    private String website ;

    @OneToMany(cascade = CascadeType.REMOVE , mappedBy = "brand" , fetch = FetchType.LAZY)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Model> models ;
    
}
