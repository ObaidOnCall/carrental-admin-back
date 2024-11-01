package ma.crm.carental.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.crm.carental.dtos.contract.ContractRequestDto;
import ma.crm.carental.dtos.contract.ContractResponseDto;
import ma.crm.carental.dtos.deliveryguy.DeliveryGuyRequestDto;
import ma.crm.carental.dtos.deliveryguy.DeliveryGuyResponseDto;
import ma.crm.carental.mappers.ContractMapper;
import ma.crm.carental.repositories.ContractRepo;

@Service
@Transactional
public class ContractService {

	private static final String ERRORMESSAGE = "access denied or unable to process the item within the contracts" ;


    private final ContractRepo contractRepo ;

    private final ContractMapper contractMapper ;

    @Autowired
    ContractService (
        ContractRepo contractRepo ,
        ContractMapper contractMapper
    ) {
        this.contractRepo = contractRepo ;
        this.contractMapper = contractMapper ;
    }
    

    public List<ContractResponseDto> saveContracts(List<ContractRequestDto> contractRequestDtos){

        return contractMapper.fromContract(
                                contractRepo.insertContractInBatch(
                                            contractMapper.toContract(contractRequestDtos)
                ));
    }

    public Map<String , Object> deleteContracts(List<Long> dguysIds) {

        Map<String , Object> serviceMessage = new HashMap<>() ;
        
        int count = contractRepo.deleteContracts(dguysIds) ;

        serviceMessage.put("status", true) ;
        serviceMessage.put("message", String.format("Number Of Deleted Contracts is %d", count)) ;
        return serviceMessage ;
        
    }
}
