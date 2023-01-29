package br.dev.diego.medic.api.infra.security;

import br.dev.diego.medic.api.infra.exceptions.TokenNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserDetailsService usuarioService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        if (tokenHeader == null && requestURI.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (tokenHeader == null) {
            throw new TokenNotFoundException("Token não encontrado!");
        }

        setUsuarioLogado(request, response, filterChain, tokenHeader);

    }

    private void setUsuarioLogado(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String tokenHeader) throws ServletException, IOException {
        String token = tokenHeader.replace("Bearer ", "");
        String subject = tokenService.getSubject(token);
        UserDetails userDetails = usuarioService.loadUserByUsername(subject);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}
