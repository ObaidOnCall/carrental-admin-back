package ma.crm.carental.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.dtos.contract.ContractRequestDto;
import ma.crm.carental.dtos.contract.ContractResponseDto;
import ma.crm.carental.dtos.deliveryguy.DeliveryGuyRequestDto;
import ma.crm.carental.dtos.deliveryguy.DeliveryGuyResponseDto;
import ma.crm.carental.dtos.vehicule.ModelResponseDto;
import ma.crm.carental.dtos.vehicule.VehResponseDto;
import ma.crm.carental.entities.Client;
import ma.crm.carental.entities.Contract;
import ma.crm.carental.entities.DeliveryGuy;
import ma.crm.carental.entities.Vehicule;


@Component
public class ContractMapper {


    public List<Contract> toContract(List<ContractRequestDto> contractRequestDtos) {


        return contractRequestDtos.stream()
                                .map(contractRequestDto ->
                                            Contract.builder()
                                            .vehicule(
                                                Vehicule.builder()
                                                .id(contractRequestDto.getVehicule())
                                                .build()
                                            )
                                            .client(
                                                Client.builder()
                                                .id(contractRequestDto.getClient())
                                                .build()
                                            )
                                            .deliveryGuy(
                                                DeliveryGuy.builder()
                                                .id(contractRequestDto.getDeliveryGuy())
                                                .build()
                                            )
                                            .numContract(contractRequestDto.getNumContract())
                                            .caution(contractRequestDto.getCaution())
                                            .days(contractRequestDto.getDays())
                                            .startDate(contractRequestDto.getStartDate())
                                            .finDate(contractRequestDto.getFinDate())
                                            .deliveryCosts(contractRequestDto.getDeliveryCosts())
                                            .price(contractRequestDto.getPrice())
                                            .preGivenPrice(contractRequestDto.getPreGivenPrice())
                                            .remainingPrice(contractRequestDto.getRemainingPrice())
                                            .placeOfContract(contractRequestDto.getPlaceOfContract())
                                            .placeOfDelivery(contractRequestDto.getPlaceOfDelivery())
                                            .placeOfReturn(contractRequestDto.getPlaceOfReturn())
                                            .dateValideDrivingLicence(contractRequestDto.getDateValideDrivingLicence())
                                            
                                            .build() 
                ).collect(Collectors.toList()) ;

    }
    

    public List<ContractResponseDto> fromContract(List<Contract> contracts) {

        return contracts.stream().map(
            contract ->ContractResponseDto.builder()
                                    .id(contract.getId())
                                    .vehicule(
                                        VehResponseDto.builder()
                                        .id(null)
                                        .matricule(contract.getVehicule().getMatricule())
                                        .model(
                                            ModelResponseDto.builder()

                                            .id(contract.getVehicule().getModel().getId())
                                            .name(contract.getVehicule().getModel().getName())
                                            .topSpeed(contract.getVehicule().getModel().getTopSpeed())
                                            .numberOfDoors(contract.getVehicule().getModel().getNumberOfDoors())
                                            .build()
                                        )
                                        .build()
                                    )
                                    .client(
                                        ClientResponseDto.builder()
                                        .id(contract.getClient().getId())
                                        .firstname(contract.getClient().getFirstname())
                                        .lastname(contract.getClient().getLastname())
                                        .cinOrPassport(contract.getClient().getCinOrPassport())
                                        .clientType(contract.getClient().getClientType())
                                        .build()
                                    )
                                    .deliveryGuy(
                                        DeliveryGuyResponseDto.builder()
                                        .id(contract.getClient().getId())
                                        .firstname(contract.getClient().getFirstname())
                                        .lastname(contract.getClient().getLastname())
                                        .cinOrPassport(contract.getClient().getCinOrPassport())
                                        .build()
                                    )
                                    .numContract(contract.getNumContract())
                                    .caution(contract.getCaution())
                                    .days(contract.getDays())
                                    .startDate(contract.getStartDate())
                                    .finDate(contract.getFinDate())
                                    .deliveryCosts(contract.getDeliveryCosts())
                                    .price(contract.getPrice())
                                    .preGivenPrice(contract.getPreGivenPrice())
                                    .remainingPrice(contract.getRemainingPrice())
                                    .placeOfContract(contract.getPlaceOfContract())
                                    .placeOfDelivery(contract.getPlaceOfDelivery())
                                    .placeOfReturn(contract.getPlaceOfReturn())
                                    .dateValideDrivingLicence(contract.getDateValideDrivingLicence())
                                    .build()
        ).collect(Collectors.toList()) ;
    }
}
