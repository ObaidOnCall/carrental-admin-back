package ma.crm.carental.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.crm.carental.annotations.ValidateClients;
import ma.crm.carental.dtos.violation.ViolationRequestDto;
import ma.crm.carental.dtos.violation.ViolationResponseDto;
import ma.crm.carental.entities.Violation;
import ma.crm.carental.mappers.ViolationMapper;
import ma.crm.carental.repositories.ViolationRepo;

@Service
@Transactional
public class ViolationService {
    

	private static final String ERRORMESSAGE = "access denied or unable to process the item within the client" ;


    private final ViolationRepo violationRepo ;
    private final ViolationMapper violationMapper ;


    @Autowired
    ViolationService(
        ViolationRepo violationRepo ,
        ViolationMapper violationMapper
    ){
        this.violationRepo = violationRepo ;
        this.violationMapper = violationMapper ;
    }
    
    @ValidateClients
    public List<ViolationResponseDto> saveViolations (
        List<ViolationRequestDto> violationRequestDtos
    ) {
        
        List<Violation> violations =  violationRepo.insertViolationInBatch(
            violationMapper.toViolation(violationRequestDtos)
        );

        return violationMapper.fromViolation(violations) ;
    }
}
