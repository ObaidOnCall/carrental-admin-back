package ma.crm.carental.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ma.crm.carental.annotations.ReactiveValidation;
import ma.crm.carental.dtos.client.ClientRequestDto;
import ma.crm.carental.dtos.client.ClientResponseDto;
import ma.crm.carental.dtos.interfaces.CreateValidationGroup;
import ma.crm.carental.dtos.interfaces.UpdateValidationGroup;
import ma.crm.carental.services.ClientService;


@RestController
@RequestMapping("/clients")
@Validated
public class ClientController {
    
    private final ClientService clientService ;

    @Autowired
    public ClientController(
            ClientService clientService
        ) {
            this.clientService = clientService ;
        }

    
    /**
     * {@link ma.crm.carental.annotations.ReactiveValidation} it validate request and generate 
     * {@link ma.crm.carental.exception.ValidationException} if there is any invaild data
    * @see validate and generate the ValidationException with errors .
    */
    @PostMapping
    @Validated(CreateValidationGroup.class)
    List<ClientResponseDto> save(
        @RequestBody @Valid List<ClientRequestDto> clientRequestDtos
    ) {

        /**
         * @see validate and generate the ValidationException with errors .
         */
        return clientService.saveClients(clientRequestDtos) ;
    }

    @DeleteMapping("/{ids}")
    Map<String , Object> delete(
        @PathVariable List<Long> ids
    ){
        return clientService.deleteClients(ids) ;
    }


    /**
     *{@link ma.crm.carental.web.ClientController.save}
     */
    @PutMapping("/{ids}")
    @Validated(UpdateValidationGroup.class)
    Map<String , Object> update(
        @PathVariable List<Long> ids ,
        @RequestBody @Valid ClientRequestDto clientRequestDto ,
        BindingResult bindingResult
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
