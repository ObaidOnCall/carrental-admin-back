package ma.crm.carental.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.crm.carental.entities.Vehicule;


public interface VehiculeRepo extends JpaRepository<Vehicule , Long>{
    Page<Vehicule> findByOwnerId(String ownerId ,Pageable pageable) ;
}
