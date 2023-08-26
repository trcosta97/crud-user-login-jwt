package br.com.hearMeOut.authentication.controller;

import br.com.hearMeOut.authentication.domain.user.DadosAutenticacao;
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
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication  = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok( new DadosTokenJWT(tokenJWT));
    }
}

