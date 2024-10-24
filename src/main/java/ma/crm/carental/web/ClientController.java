package ma.crm.carental.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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


    @PutMapping("/{ids}")
    Map<String , Object> update(
        @PathVariable List<Long> ids ,
        @RequestBody ClientRequestDto clientRequestDto 
    ){

        List<ClientRequestDto> clientRequestDtos = new ArrayList<>();
        clientRequestDtos.add(clientRequestDto);
        return clientService.updateClients(ids, clientRequestDtos) ;
    }

    @GetMapping
    List<ClientResponseDto> pagenateClients(
        @RequestParam int page ,
        @RequestParam int pageSize
    ) {

        return clientService.pagenateClients(page, pageSize) ;
    }

    @GetMapping("/{id}")
    ClientResponseDto findCient(
        @PathVariable long id 
    ) {
        return clientService.findClient(id) ;
    }

}
