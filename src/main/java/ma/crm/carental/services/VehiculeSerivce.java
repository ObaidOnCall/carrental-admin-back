package ma.crm.carental.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.crm.carental.dto.VehRequsetDto;
import ma.crm.carental.dto.VehResponseDto;
import ma.crm.carental.entities.Vehicule;
import ma.crm.carental.mappers.VehiculeMapper;
import ma.crm.carental.repositories.VehiculeRepo;

@Service
public class VehiculeSerivce {
    

    private final VehiculeRepo vehiculeRepo ;
    private final VehiculeMapper vehiculeMapper ;

    @Autowired
    public VehiculeSerivce (VehiculeRepo vehiculeRepo , VehiculeMapper vehiculeMapper) {
        this.vehiculeRepo = vehiculeRepo ;
        this.vehiculeMapper = vehiculeMapper ;
    }


    public List<VehResponseDto> saveVehs(List<VehRequsetDto> vehrequests , String ownerID) {

        List<Vehicule> vehicules = vehiculeMapper.toVeh(vehrequests , ownerID) ;

        return vehiculeMapper.fromVeh(vehiculeRepo.saveAllAndFlush(vehicules)) ;

    }

    public Page<Vehicule> vehsPaginate(String owner ,int page , int limt) {
        return vehiculeRepo.findByOwnerId(owner, PageRequest.of(page, limt)) ;
    }

}
