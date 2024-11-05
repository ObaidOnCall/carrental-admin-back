package ma.crm.carental.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.crm.carental.dtos.charge.ChargeRequestDto;
import ma.crm.carental.dtos.charge.ChargeResponseDto;
import ma.crm.carental.dtos.contract.ContractRequestDto;
import ma.crm.carental.dtos.contract.ContractResponseDto;
import ma.crm.carental.mappers.ChargeMapper;
import ma.crm.carental.repositories.ChargeRepo;

@Service
@Transactional
public class ChargeService {

    private static final String ERRORMESSAGE = "Access denied or unable to process the item within the contracts. Contract ID: ";

    private final ChargeMapper chargeMapper ;
    private final ChargeRepo chargeRepo ;

    ChargeService(
        ChargeMapper chargeMapper ,
        ChargeRepo chargeRepo
    ) {
        this.chargeMapper = chargeMapper ;
        this.chargeRepo = chargeRepo ;
    }

    
    public List<ChargeResponseDto> saveCharges(List<ChargeRequestDto> chargeRequestDtos){

        return chargeMapper.fromCharge(
                                chargeRepo.insertChargeInBatch(
                                            chargeMapper.toCharge(chargeRequestDtos)
                ));
    }

    public Map<String , Object> deleteCharges(List<Long> chargesIds) {

        Map<String , Object> serviceMessage = new HashMap<>() ;
        
        int count = chargeRepo.deleteCharges(chargesIds) ;

        serviceMessage.put("status", true) ;
        serviceMessage.put("message", String.format("Number Of Deleted Charges is %d", count)) ;
        return serviceMessage ;
        
    }
    
    
    public Map<String , Object> updateCharges(List<Long> chargesIds , List<ChargeRequestDto> chargeRequestDtos) {

        Map<String , Object> serviceMessage = new HashMap<>() ;

        int count = chargeRepo.updateChargesInBatch(chargesIds, chargeMapper.toCharge(chargeRequestDtos).get(0)) ;

        serviceMessage.put("status", true) ;
        serviceMessage.put("message", "Number Of Updated Charges is " + count) ;

        return serviceMessage ;
    }
}
