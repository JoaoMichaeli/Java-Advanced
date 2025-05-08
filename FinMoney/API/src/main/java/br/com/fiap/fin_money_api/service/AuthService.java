package br.com.fiap.fin_money_api.service;

import br.com.fiap.fin_money_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Ensina o Spring a detectar os usuários
@Service
public class AuthService implements UserDetailsService { // Transforma o AuthService em um UserDetailService

    @Autowired
    private UserRepository repository;

    // Assinatura para buscar o usuário pelo nome
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("usuário não encontrado")
        );
    }
}
