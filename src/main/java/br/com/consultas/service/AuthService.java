package br.com.consultas.service;

import br.com.consultas.model.Usuario;
import br.com.consultas.repository.UsuarioRepository;
import br.com.consultas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public String login(String username, String password) {

        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(password))
            throw new RuntimeException("Senha inválida");

        return jwtUtil.gerarToken(username);
    }
}