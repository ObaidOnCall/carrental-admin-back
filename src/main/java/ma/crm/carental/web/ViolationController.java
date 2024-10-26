package ma.crm.carental.web;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ma.crm.carental.annotations.ValidateRequest;
import ma.crm.carental.dtos.violation.ViolationRequestDto;
import ma.crm.carental.dtos.violation.ViolationResponseDto;
import ma.crm.carental.services.ViolationService;


@RestController
@RequestMapping("/violations")
public class ViolationController {
    

    private final ViolationService violationService ;


    ViolationController(
        ViolationService violationService
    ){
        this.violationService = violationService ;
    }

    @PostMapping
    @ValidateRequest
    List<ViolationResponseDto> save(
        @Valid @RequestBody List<ViolationRequestDto> violationRequestDtos ,
        BindingResult bindingResult
    ){
        return violationService.saveViolations(violationRequestDtos) ;
    }
}
