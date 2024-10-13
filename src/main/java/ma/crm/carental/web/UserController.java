package ma.crm.carental.web;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
import ma.crm.carental.dtos.UserCreateDto;
import ma.crm.carental.dtos.UserResponseDto;
import ma.crm.carental.dtos.UserUpdateDto;
import ma.crm.carental.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService ;

    

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService ;
    }
    
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateDto userRequestDto) throws IOException{
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
        return userService.createUser(userRequestDto, jwt.getTokenValue()) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto , @PathVariable String id) throws IOException{
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
        return userService.updateUser(userUpdateDto, id ,jwt.getTokenValue()) ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id) throws IOException{
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
        return userService.deleteUser( id ,jwt.getTokenValue()) ;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> showUsers(@RequestParam int page , @RequestParam int size) throws IOException{
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
        return userService.showUsers( page * size, size ,jwt.getTokenValue()) ;
    }

    @GetMapping("/test-user")
    public ResponseEntity<Map<String,Object>> testUser(
        Authentication authentication
    ){
        Map<String, Object> response = new HashMap<>();
        
        // Get the JWT from the authentication
        Jwt jwt = (Jwt) authentication.getPrincipal();
        
        // Extract user info (e.g., username)
        String username = jwt.getClaimAsString("preferred_username");
        
        // Extract organization and roles (use your custom converter here)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> roles = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());

        // Fill the response
        response.put("username", username);
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }
    
}
