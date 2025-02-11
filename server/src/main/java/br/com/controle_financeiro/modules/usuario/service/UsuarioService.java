package br.com.controle_financeiro.modules.usuario.service;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.controle_financeiro.exception.EntityNotFoundException;
import br.com.controle_financeiro.modules.usuario.dto.LoginUsuarioDto;
import br.com.controle_financeiro.modules.usuario.entity.UsuarioEntity;
import br.com.controle_financeiro.modules.usuario.repository.UsuarioRepository;
import br.com.controle_financeiro.modules.usuario.security.UserDetailsImpl;
import br.com.controle_financeiro.springboot.security.dto.RecoveryJwtTokenDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private AuthenticationManager authenticationManager;
    private UsuarioTokenService jwtTokenService;
    private PasswordEncoder passwordEncoder;

    public RecoveryJwtTokenDto authenticateUser(LoginUsuarioDto loginUsuarioDTO) throws AuthenticationException {

        UsuarioEntity usuario = usuarioRepository.findByEmail(loginUsuarioDTO.email())
                .orElseThrow(() -> new EntityNotFoundException("Usuário", loginUsuarioDTO.email()));

        boolean passwordMatches = this.passwordEncoder.matches(loginUsuarioDTO.senha(), usuario.getSenha());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(usuario);

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

}
