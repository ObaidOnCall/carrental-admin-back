package ma.crm.carental.repositories;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.crm.carental.entities.Vehicule;


public interface VehiculeRepo extends JpaRepository<Vehicule , Long>{
    
    Page<Vehicule> findByTenantId(String tenantId ,Pageable pageable) ;

    @Modifying
    @Query(value = "UPDATE vehicules v SET v.matricule = COALESCE(:matricule, v.matricule),"+
                    "v.model_id = COALESCE(:model, v.model_id), "+
                    "v.price = COALESCE(:price, v.price) , " +
                    "v.metadata = COALESCE(:metadata, v.metadata) "+
                    "WHERE v.id IN :ids AND v.tenant_id =:tenant_id" , nativeQuery = true) 
    int updateVehiculesInBatch(
        @Param("ids") List<Long> ids ,
        @Param("tenant_id") String tenant ,
        @Param("matricule") String matricule ,
        @Param("model") Long model ,
        @Param("price") double price ,
        @Param("metadata") String metadata 
    ) ;

    @Query(value = "SELECT v FROM Vehicule v WHERE v.id =:id AND v.tenantId =:tenant_id")
    Optional<Vehicule> findByIdAndTenantId(
        @Param("id") Long id ,
        @Param("tenant_id") String tenant
    ) ;
}
