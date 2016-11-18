package br.ucdb.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by CYNNER on 15/11/2016.
 */
public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String header = ((HttpServletRequest) servletRequest).getHeader("Autorizacao");

        if(header==null || !header.startsWith("Bearer ")){
            throw new ServletException("Token Invalido ou Inesistente");
        }
        String token =header.substring(7); // extarindo token

        // Verificar e validar token
        try {
            Jwts.parser().setSigningKey("dicionario").parseClaimsJws(token).getBody();
        }catch (SignatureException e){
            throw new ServletException("Token Invalido ");
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
