package br.com.hearMeOut.authentication.controller;

import br.com.hearMeOut.authentication.domain.address.Address;
import br.com.hearMeOut.authentication.domain.user.User;
import br.com.hearMeOut.authentication.domain.user.UserSignInData;
import br.com.hearMeOut.authentication.domain.user.UserUpdateData;
import br.com.hearMeOut.authentication.service.AddressService;
import br.com.hearMeOut.authentication.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody UserSignInData data, UriComponentsBuilder uriBuilder){
        var user = new User(data);
        var address = new Address(data.address());

        Address savedAddress = addressService.save(address);
        user.setAddress(savedAddress);

        User savedUser = userService.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<User> getUser(@RequestParam Long id){
        var user = userService.get(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<List<User>> getAllUsers(){
        var allUsers = userService.getAll();
        return ResponseEntity.ok(allUsers);
    }


    @PutMapping("/{id}")
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<User> update(@RequestBody UserUpdateData data, @RequestParam Long id){
        var user = new User(data);
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name="bearer-key")
    public ResponseEntity<User> delete(@RequestParam Long id){
        return ResponseEntity.ok(userService.delete(id));
    }
}
