package br.com.clinicapi.service;

import br.com.clinicapi.domain.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        // simples: usa secret como bytes
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(Usuario usuario) {
        var agora = new Date();
        var validade = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setIssuedAt(agora)
                .setExpiration(validade)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenValido(String token, String usernameEsperado) {
        String username = getUsernameFromToken(token);
        return username.equals(usernameEsperado) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        var expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }
}
