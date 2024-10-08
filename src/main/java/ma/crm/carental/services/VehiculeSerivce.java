package ma.crm.carental.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ma.crm.carental.dtos.AssuranceRequestDto;
import ma.crm.carental.dtos.AssuranceResponseDto;
import ma.crm.carental.dtos.VehRequsetDto;
import ma.crm.carental.dtos.VehResponseDto;
import ma.crm.carental.entities.Assurance;
import ma.crm.carental.entities.Vehicule;
import ma.crm.carental.mappers.VehiculeMapper;
import ma.crm.carental.repositories.AssuranceRepo;
import ma.crm.carental.repositories.VehiculeRepo;


@Service
@Transactional
public class VehiculeSerivce {
    

    private final VehiculeRepo vehiculeRepo ;
    private final AssuranceRepo assuranceRepo ;
    private final VehiculeMapper vehiculeMapper ;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public VehiculeSerivce (
            VehiculeRepo vehiculeRepo , 
            VehiculeMapper vehiculeMapper ,
            AssuranceRepo assuranceRepo
        ) {
        this.vehiculeRepo = vehiculeRepo ;
        this.vehiculeMapper = vehiculeMapper ;
        this.assuranceRepo = assuranceRepo ;
    }


    public List<VehResponseDto> saveVehs(List<VehRequsetDto> vehrequests) {

        List<Vehicule> vehicules = vehiculeMapper.toVeh(vehrequests) ;
        vehiculeRepo.saveAllAndFlush(vehicules) ;

        entityManager.clear();
        return vehiculeMapper.fromVeh(vehicules) ;

    }

    public Page<Vehicule> vehsPaginate(int page , int limt) {
        return vehiculeRepo.findAll(PageRequest.of(page, limt)) ;
    }

    /**
     * @apiNote assurance sevice fn
     */


    public List<AssuranceResponseDto> saveAssurances(List<AssuranceRequestDto> assuranceRequestDtos) {

        List<Assurance> assurances = vehiculeMapper.toAssurances(assuranceRequestDtos) ;
        assuranceRepo.saveAllAndFlush(assurances) ;

        entityManager.clear();
        return vehiculeMapper.fromAssurances(assurances) ;

    }
}
