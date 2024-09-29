package ma.crm.carental.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import ma.crm.carental.dto.VehRequsetDto;
import ma.crm.carental.dto.VehResponseDto;
import ma.crm.carental.entities.Vehicule;

@Component
public class VehiculeMapper {
    

    public List<Vehicule> toVeh (List<VehRequsetDto> vehRequsetDtos , String ownerID) {

        List<Vehicule> vehicules = new ArrayList<>() ;

        for (VehRequsetDto vehReq : vehRequsetDtos) {

            Vehicule vehicule = new Vehicule() ;
            BeanUtils.copyProperties(vehReq, vehicule);
            /**
             * @@set the ownerID
             */

            vehicule.setOwnerId(ownerID);
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
}
