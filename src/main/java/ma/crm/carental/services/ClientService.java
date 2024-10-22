package ma.crm.carental.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.crm.carental.dtos.client.ClientRequestDto;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.mappers.ClientMapper;
import ma.crm.carental.repositories.ClientRepo;
import ma.crm.carental.tenantfilter.TenantContext;


@Service
@Transactional
public class ClientService {
    


    private final ClientRepo clientRepo ;
    private final ClientMapper clientMapper ;

    @Autowired
    ClientService(
            ClientRepo clientRepo ,
            ClientMapper clientMapper 
        ) {
        this.clientRepo = clientRepo ;
        this.clientMapper = clientMapper ;
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
}
