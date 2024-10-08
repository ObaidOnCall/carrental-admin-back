package ma.crm.carental.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import ma.crm.carental.dtos.AssuranceRequestDto;
import ma.crm.carental.dtos.AssuranceResponseDto;
import ma.crm.carental.dtos.VehRequsetDto;
import ma.crm.carental.dtos.VehResponseDto;
import ma.crm.carental.entities.Assurance;
import ma.crm.carental.entities.Brand;
import ma.crm.carental.entities.Model;
import ma.crm.carental.entities.Vehicule;

@Component
public class VehiculeMapper {
    

    public List<Vehicule> toVeh (List<VehRequsetDto> vehRequsetDtos) {

        List<Vehicule> vehicules = new ArrayList<>() ;

        for (VehRequsetDto vehReq : vehRequsetDtos) {

            Vehicule vehicule = new Vehicule() ;
            
            // vehicule.setBrand(Brand.builder()
            //                         .id(vehReq.getBrand())
            //                         .build()
            // );

            vehicule.setModel(Model.builder()
                                    .id(vehReq.getModel())
                                    .build()
            );
            BeanUtils.copyProperties(vehReq, vehicule);
            
            vehicules.add(vehicule) ;
        }


        return vehicules ;
    }

    public List<VehResponseDto> fromVeh (List<Vehicule> vehicules) {

        List<VehResponseDto> vehResponses = new ArrayList<>() ;

        for (Vehicule veh : vehicules) {

            VehResponseDto vehresponse = new VehResponseDto() ;
            BeanUtils.copyProperties(veh, vehresponse);
            vehResponses.add(vehresponse) ;
        }

        return vehResponses ;
    }

    /**
     * @apiNote Aussrance mappers fn
     */

     public List<Assurance> toAssurances (List<AssuranceRequestDto> AssuranceRequestDtos) {

        List<Assurance> assurances = new ArrayList<>() ;

        for (AssuranceRequestDto assreq : AssuranceRequestDtos) {

            Assurance assurance = new Assurance() ;
            Vehicule vehicule = new Vehicule() ;
            vehicule.setId(assreq.getVehicle());

            assurance.setVehicule(Vehicule.builder()
                                            .id(assreq.getVehicle())
                                            .build());

            BeanUtils.copyProperties(assreq, assurance);
            
            assurances.add(assurance) ;
        }


        return assurances ;
    }


    public List<AssuranceResponseDto> fromAssurances (List<Assurance> assurances) {

        List<AssuranceResponseDto> assuranceResponseDtos = new ArrayList<>() ;

        for (Assurance assurance : assurances) {

            AssuranceResponseDto vehresponse = new AssuranceResponseDto() ;
            BeanUtils.copyProperties(assurance, vehresponse);
            assuranceResponseDtos.add(vehresponse) ;
        }

        return assuranceResponseDtos ;
    }
}
