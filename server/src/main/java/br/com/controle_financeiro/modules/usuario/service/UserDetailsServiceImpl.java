package br.com.controle_financeiro.modules.usuario.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.repository.UsuarioRepository;
import br.com.controle_financeiro.modules.usuario.security.UserDetailsImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário", username));
        return new UserDetailsImpl(usuario);
    }
}
