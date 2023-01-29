package br.dev.diego.medic.api.controllers;

import br.dev.diego.medic.api.domain.entities.Usuario;
import br.dev.diego.medic.api.domain.requests.UsuarioRequestRecord;
import br.dev.diego.medic.api.domain.responses.TokenJWTResponse;
import br.dev.diego.medic.api.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private AuthenticationManager manager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTResponse> efetuarLogin(@RequestBody @Valid UsuarioRequestRecord user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.login(), user.senha());
        Authentication authenticate = manager.authenticate(token);
        return ResponseEntity.ok(tokenService.gerarTokenJWT((Usuario) authenticate.getPrincipal()));
    }

}