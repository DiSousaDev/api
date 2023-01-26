package br.dev.diego.medic.api.domain.service.impl;

import br.dev.diego.medic.api.domain.repositories.UsuarioRepository;
import br.dev.diego.medic.api.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

}
