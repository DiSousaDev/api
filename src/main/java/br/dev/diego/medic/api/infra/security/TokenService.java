package br.dev.diego.medic.api.infra.security;

import br.dev.diego.medic.api.domain.entities.Usuario;
import br.dev.diego.medic.api.domain.responses.TokenJWTResponse;
import br.dev.diego.medic.api.infra.exceptions.GerarTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Medic")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(getDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new GerarTokenException("Erro ao gerar token JWT, " + e.getMessage());
        }
    }

    public TokenJWTResponse gerarTokenJWT(Usuario usuario) {
        return new TokenJWTResponse(gerarToken(usuario));
    }

    private Instant getDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
