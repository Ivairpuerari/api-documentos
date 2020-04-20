/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eav.documentos.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ivaai
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secret;
    
    private Claims getClaimsFromToken(String token) {
        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        
        Claims claims;
        try {
            claims = Jwts.parserBuilder().setSigningKey(secretKey)
                     .build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date getExpirationDateFromToken(String token) {
        Date dateExpiration;
        try {
            Claims claims = getClaimsFromToken(token);
            dateExpiration = claims.getExpiration();
        } catch (Exception e) {
            dateExpiration = null;
        }
        return dateExpiration;
    }
    
    public String getUsername(String token) {
        Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean tokenValido(String token) {
        Date dataExpiracao = this.getExpirationDateFromToken(token);
        if (dataExpiracao == null) {
            return false;
        }
        return dataExpiracao.after(new Date());
    }
}
