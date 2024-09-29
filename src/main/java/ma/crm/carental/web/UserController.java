package ma.crm.carental.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import jakarta.websocket.server.PathParam;
import ma.crm.carental.dto.UserCreateDto;
import ma.crm.carental.dto.UserResponseDto;
import ma.crm.carental.dto.UserUpdateDto;
import ma.crm.carental.services.UserService;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
}
