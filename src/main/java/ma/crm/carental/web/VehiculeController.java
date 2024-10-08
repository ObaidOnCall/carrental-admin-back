package ma.crm.carental.web;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ma.crm.carental.dtos.AssuranceRequestDto;
import ma.crm.carental.dtos.AssuranceResponseDto;
import ma.crm.carental.dtos.VehRequsetDto;
import ma.crm.carental.dtos.VehResponseDto;
import ma.crm.carental.entities.Vehicule;
import ma.crm.carental.services.VehiculeSerivce;


@RestController
@RequestMapping("/vehicules")
public class VehiculeController {
    
    private final VehiculeSerivce vehiculeSerivce ;

    public VehiculeController (VehiculeSerivce vehiculeSerivce) {
        this.vehiculeSerivce = vehiculeSerivce ;
    }


    @PostMapping
    public List<VehResponseDto> save(
            @Valid @RequestBody List<VehRequsetDto> vehrequestList 
        ) {
            
        return vehiculeSerivce.saveVehs(vehrequestList ) ;
    }

    @GetMapping()
    public Page<Vehicule> paginate(@RequestParam int page , @RequestParam int size) {
        /**
         * @@ get the current authentication object form SecurityContextHolder .
         */
        return vehiculeSerivce.vehsPaginate(page, size) ;
    }

    // @DeleteMapping("/{ids}")
    // public List<VehResponseDto> delete(
    //     @PathVariable long ids
    // ) {
    //     //delete .
    // }

    /**
     * @apiNote assurance conttorerlerfkkfkjfkjjdfjjf
     */

    @PostMapping("/assurances")
    public List<AssuranceResponseDto> saveAssurances(@Valid @RequestBody List<AssuranceRequestDto> assuranceRequestDtos) {
        return vehiculeSerivce.saveAssurances(assuranceRequestDtos) ;
    }






    @GetMapping("/test")
    public void test(){

        SecurityContext context = SecurityContextHolder.createEmptyContext() ;
        Authentication authentication = new TestingAuthenticationToken("username", "password" , "ROLE_USER") ;
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        /**
         * @see access to the security context holder
         */

         Authentication authentication_ = SecurityContextHolder.getContext().getAuthentication() ;
         Jwt jwt = (Jwt) authentication.getPrincipal() ;

    }
}
