package br.com.fiap.fin_money_api.config;

import br.com.fiap.fin_money_api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Validar o Header
        var header = request.getHeader("Autorization");
        if(header == null){
            filterChain.doFilter(request, response);
            return;
        }

        // Validar Bearer
        if(!header.startsWith("Bearer ")){
            response.setStatus(401);
            response.getWriter().write("""
                    {'message': 'Authorization deve iniciar com Bearer'}
                    """);
            return;
        }

        // Verificar o JWT
        var jwt = header.replace("Bearer ", "");
        var user = tokenService.getUserFromToken(jwt);

        // Autenticar o usuário
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
