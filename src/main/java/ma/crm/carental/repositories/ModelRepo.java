package ma.crm.carental.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.crm.carental.entities.Model;


/**
 * ModelRepo
 */
public interface ModelRepo extends JpaRepository<Model , Long>{
    
    @Modifying
    @Query(value = "UPDATE models m SET m.name = COALESCE(:name, m.name),"+
                    "m.brand_id = COALESCE(:brand, m.brand_id), "+
                    "m.top_speed = COALESCE(:top_speed, m.top_speed),"+
                    "m.number_of_doors = COALESCE(:number_of_doors, m.number_of_doors)"+
                    "WHERE m.id IN :ids AND m.tenant_id =:tenant_id" , nativeQuery = true) 
    int updateModelsInBatch(
        @Param("ids") List<Long> ids ,
        @Param("tenant_id") String tenant ,
        @Param("brand") Long brand ,
        @Param("name") String name ,
        @Param("top_speed") int topSpeed ,
        @Param("number_of_doors") int numberOfDoors 
    ) ;


    //we will add aslo the low level Params updating batch .
}
