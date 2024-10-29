package ma.crm.carental.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ma.crm.carental.dtos.client.ClientRequestDto;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.dtos.violation.ViolationRequestDto;
import ma.crm.carental.dtos.violation.ViolationResponseDto;
import ma.crm.carental.entities.Charge;
import ma.crm.carental.entities.Client;
import ma.crm.carental.entities.Violation;

@Component
public class ViolationMapper {
    

    public List<Violation> toViolation(List<ViolationRequestDto> violationRequestDtos) {


        return violationRequestDtos.stream()
                                .map(violationRequestDto ->
                                            Violation.builder()
                                            .description(violationRequestDto.getDescription())
                                            .finAmount(violationRequestDto.getFinAmount())
                                            .isPaid(violationRequestDto.isPaid())
                                            .client(Client.builder().id(violationRequestDto.getClient()).build())
                                            .charge(
                                                violationRequestDto.getCharge() != null && violationRequestDto.getCharge() != 0 
                                                ? Charge.builder().id(violationRequestDto.getCharge()).build() 
                                                : null
                                            )
                                            .date(violationRequestDto.getDate())
                                            .build()
                ).collect(Collectors.toList()) ;

    }

    public List<ViolationResponseDto> fromViolation(List<Violation> violations) {

        // .client(
        //     ClientResponseDto.builder()
        //                     .firstname(violation.getClient().getFirstname())
        //                     .lastname(violation.getClient().getLastname())
        //                     .id(violation.getClient().getId())
        //                     .build()
        // )
        return violations.stream().map(
            violation ->    ViolationResponseDto.builder()
                            .id(violation.getId())
                            .description(violation.getDescription())
                            .finAmount(violation.getFinAmount())
                            .isPaid(violation.getIsPaid())
                            .client(ClientResponseDto.builder()
                                                        .id(violation.getClient().getId())
                                                        .firstname(violation.getClient().getFirstname())
                                                        .lastname(violation.getClient().getLastname())
                                                        .email(violation.getClient().getEmail())
                                                        .address(violation.getClient().getAddress())
                                                        .ville(violation.getClient().getVille())
                                                        .phone1(violation.getClient().getPhone1())
                                                        .phone2(violation.getClient().getPhone2())
                                                        .cinOrPassport(violation.getClient().getCinOrPassport())
                                                        .cinIsValideUntil(violation.getClient().getCinIsValideUntil())
                                                        .licence(violation.getClient().getLicence())
                                                        .licenceIsValideUntil(violation.getClient().getLicenceIsValideUntil())
                                                        .build()
                            )
                            .date(violation.getDate()) 
                            .build()
        ).collect(Collectors.toList()) ;
    }
}
