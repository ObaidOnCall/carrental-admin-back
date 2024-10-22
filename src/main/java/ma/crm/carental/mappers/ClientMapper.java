package ma.crm.carental.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ma.crm.carental.dtos.client.ClientRequestDto;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.entities.Client;



@Component
public class ClientMapper {
    

    public List<Client> toClient(List<ClientRequestDto> clientRequestDtos) {


        return clientRequestDtos.stream()
                                .map(clientRequestDto ->
                                            Client.builder()
                                            .firstname(clientRequestDto.getFirstname())
                                            .lastname(clientRequestDto.getLastname())
                                            .address(clientRequestDto.getAddress())
                                            .email(clientRequestDto.getEmail())
                                            .ville(clientRequestDto.getVille())
                                            .nationality(clientRequestDto.getNationality())
                                            .clientType(clientRequestDto.getClientType())
                                            .cinOrPassport(clientRequestDto.getCinOrPassport())
                                            .cinIsValideUntil(clientRequestDto.getCinIsValideUntil())
                                            .phone1(clientRequestDto.getPhone1())
                                            .phone2(clientRequestDto.getPhone2())
                                            .codePostal(clientRequestDto.getCodePostal())
                                            .build() 
                ).collect(Collectors.toList()) ;

    }


    public List<ClientResponseDto> fromClient(List<Client> clients) {

        return clients.stream().map(
            client ->ClientResponseDto.builder()
                                    .firstname(client.getFirstname())
                                    .lastname(client.getLastname())
                                    .email(client.getEmail())
                                    .codePostal(client.getCodePostal())
                                    .address(client.getAddress())
                                    .clientType(client.getClientType())
                                    .cinOrPassport(client.getCinOrPassport())
                                    .cinIsValideUntil(client.getCinIsValideUntil())
                                    .licence(client.getLicence())
                                    .licenceIsValideUntil(client.getLicenceIsValideUntil())
                                    .ville(client.getVille())
                                    .phone1(client.getPhone1())
                                    .phone2(client.getPhone2())
                                    .build()
        ).collect(Collectors.toList()) ;
    }
}
