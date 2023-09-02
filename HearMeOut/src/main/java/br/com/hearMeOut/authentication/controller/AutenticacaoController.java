package br.com.hearMeOut.authentication.controller;

import br.com.hearMeOut.authentication.domain.user.AuthenticationData;
import br.com.hearMeOut.authentication.domain.user.User;
import br.com.hearMeOut.authentication.infra.security.DadosTokenJWT;
import br.com.hearMeOut.authentication.infra.security.TokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @CrossOrigin(origins="*")
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AuthenticationData dados) {
        System.out.println("Começou");

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        System.out.println("Buscou senha e login");

        var authentication  = manager.authenticate(authenticationToken);
        System.out.println("Autenticou");

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());
        System.out.println("Gerou token");

        return ResponseEntity.ok( new DadosTokenJWT(tokenJWT));
    }
}

