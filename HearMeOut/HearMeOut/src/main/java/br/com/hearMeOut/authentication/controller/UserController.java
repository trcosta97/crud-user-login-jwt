package br.com.hearMeOut.authentication.controller;

import br.com.hearMeOut.authentication.domain.user.User;
import br.com.hearMeOut.authentication.domain.user.UserSignInData;
import br.com.hearMeOut.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody UserSignInData data, UriComponentsBuilder uriBuilder){
        var user = new User(data);
        User savedUser = userService.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }
}
