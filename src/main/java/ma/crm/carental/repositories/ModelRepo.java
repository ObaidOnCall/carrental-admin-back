package ma.crm.carental.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.crm.carental.entities.Brand;
import ma.crm.carental.entities.Model;


/**
 * ModelRepo
 */
public interface ModelRepo extends JpaRepository<Model , Long>{
    // List<Brand> findByOwnerId(String ownerId) ;
}
