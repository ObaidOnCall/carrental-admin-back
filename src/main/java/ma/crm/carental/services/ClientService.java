package ma.crm.carental.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import jakarta.persistence.NoResultException;
import ma.crm.carental.dtos.client.ClientRequestDto;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.dtos.violation.ViolationResponseDto;
import ma.crm.carental.entities.Client;
import ma.crm.carental.exception.UnableToProccessIteamException;
import ma.crm.carental.mappers.ClientMapper;
import ma.crm.carental.repositories.ClientRepo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Service
@Transactional
public class ClientService {
    
	private static final String ERRORMESSAGE = "access denied or unable to process the item within the client" ;



    private final ClientRepo clientRepo ;
    private final ClientMapper clientMapper ;
    private final Validator validator ;

    @Autowired
    ClientService(
            ClientRepo clientRepo ,
            ClientMapper clientMapper ,
            Validator validator 
        ) {
        this.clientRepo = clientRepo ;
        this.clientMapper = clientMapper ;
        this.validator = validator ;
    }


    public List<ClientResponseDto> saveClients(List<ClientRequestDto> clientRequestDtos){

        return clientMapper.fromClient(
                                clientRepo.insertClientInBatch(
                                            clientMapper.toClient(clientRequestDtos)
                            ));
    }

    public Map<String , Object> deleteClients(List<Long> clientsIds) {

        Map<String , Object> serviceMessage = new HashMap<>() ;
        
        int count = clientRepo.deleteClients(clientsIds) ;

        serviceMessage.put("status", true) ;
        serviceMessage.put("message", "Number Of Deleted Client is :" + count) ;

        return serviceMessage ;
        
    }

    public Map<String , Object> updateClients(List<Long> clientsIds , List<ClientRequestDto> clients) {

        Map<String , Object> serviceMessage = new HashMap<>() ;


        int count = clientRepo.updateClientsInBatch(clientsIds, clientMapper.toClient(clients).get(0)) ;

        serviceMessage.put("status", true) ;
        serviceMessage.put("message", "Number Of Updated Clients is :" + count) ;

        return serviceMessage ;
    }



    public Page<ClientResponseDto> pagenateClients(Pageable pageable) {
        
        Long totalElements = clientRepo.count() ;


        List<ClientResponseDto> clientResponseDtos = clientMapper.fromClient(
            clientRepo.clientsWithPagination(pageable.getPageNumber(), pageable.getPageSize())
        ) ;

        return new PageImpl<>(clientResponseDtos , pageable , totalElements) ;


    }

    public ClientResponseDto findClient(long id) {

        
        try {
            Client client = clientRepo.find(id) ;

            /**
             * @convert the client object to List of Client to use the general mapper
             */
            List<Client> clients = List.of(client) ;

            return clientMapper.fromClient(clients).get(0) ;

        } catch (NoResultException e) {
            throw new UnableToProccessIteamException(ClientService.ERRORMESSAGE) ;
        }
    }

}
