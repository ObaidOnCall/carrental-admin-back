package ma.crm.carental.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ma.crm.carental.dtos.client.ClientRequestDto;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final ClientService clientService ;

    @Autowired
    public ClientController(
            ClientService clientService
        ) {
            this.clientService = clientService ;
        }


    @PostMapping
    List<ClientResponseDto> save(
        @Valid @RequestBody List<ClientRequestDto> clientRequestDtos
    ) {

        return clientService.saveClients(clientRequestDtos) ;
    }

    @DeleteMapping("/{ids}")
    Map<String , Object> delete(
        @PathVariable List<Long> ids
    ){
        return clientService.deleteClients(ids) ;
    }
}
