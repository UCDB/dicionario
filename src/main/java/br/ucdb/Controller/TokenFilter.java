package br.ucdb.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String header = ((HttpServletRequest) servletRequest).getHeader("Authorization");

        if(header==null || !header.startsWith("Bearer ")){
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Invalido ou Inesistente");
           // throw new ServletException("Token Invalido ou Inesistente");
        }else {
            // Verificar e validar token
            try {
                String token =header.substring(7); // extarindo token
                Jwts.parser().setSigningKey("dicionario").parseClaimsJws(token).getBody();
            }catch (SignatureException e){
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Invalido");
                // throw new ServletException("Token Invalido ");
            }
            catch (io.jsonwebtoken.ExpiredJwtException e){
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token Expirado");

            }
        }




        filterChain.doFilter(servletRequest,servletResponse);

    }
}
