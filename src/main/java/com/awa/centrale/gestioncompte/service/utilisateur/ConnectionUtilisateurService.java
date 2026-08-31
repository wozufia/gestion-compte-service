package com.awa.centrale.gestioncompte.service.utilisateur;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.AuthReponse;
import com.awa.centrale.gestioncompte.model.ConnectionRequete;
import com.awa.centrale.gestioncompte.model.Role;
import com.awa.centrale.gestioncompte.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConnectionUtilisateurService {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Value("${jwt.secret.key}")
    private String jwtSecret;

    public AuthReponse genererToken(ConnectionRequete detailConnection) {
        Utilisateur utilisateur = obtenirUtilisateur(detailConnection);
        if (utilisateur == null) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec les informations fournies.");
        }

        Map<String, Object> claims = new HashMap<>();
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        List<String> roles = utilisateur.getRoles() == null
                ? List.of()
                : utilisateur.getRoles().stream()
                        .map(Role::getName)
                        .toList();

        claims.put("roles", roles);

        Date expiration = Date.from(Instant.now().plus(15, ChronoUnit.MINUTES));
        String token = Jwts.builder()
                .subject(utilisateur.getFirstName() + " " + utilisateur.getLastName())
                .claims(claims)
                .issuedAt(Date.from(Instant.now()))
                .expiration(expiration)
                .signWith(key)
                .compact();

        AuthReponse authReponse = new AuthReponse();
        authReponse.setJwt(token);
        authReponse.setTokenType("Bearer");
        return authReponse;
    }

    private Utilisateur obtenirUtilisateur(ConnectionRequete detailConnection) {
        return utilisateurDao.obtenirUtilisateurParEmailEtMotDePasse(
                detailConnection.getEmail(),
                detailConnection.getMotDePasse()
        );
    }
}
