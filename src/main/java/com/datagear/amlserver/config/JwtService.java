package com.datagear.amlserver.config;

import com.datagear.amlserver.entity.auth.Capability;
import com.datagear.amlserver.entity.auth.Group;
import com.datagear.amlserver.entity.auth.RegisterRequest;
import com.datagear.amlserver.entity.auth.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class JwtService {
    private static final String SECRET_KEY = "5e9393025feb192fb7d167d08a5d039098146b3456cd92ba98c095df5c62c932";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            User userDetails
    ) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", userDetails.getFirstName());
        payload.put("lastname", userDetails.getLastName());
//        List<String> roles = userDetails.getRole().stream().map(role -> role.getName()).collect(Collectors.toList());
        payload.put("role", userDetails.getRole());
        Set<Group> groups = userDetails.getGroups();
//        payload.put("group", groups);
        payload.put("group", groups.stream().map(Group::getName).collect(Collectors.toList()));
        Set<String> capabilities = new HashSet<>();
        groups.forEach(group -> group.getCapabilities().forEach(capability -> capabilities.add(capability.getName())));
        payload.put("capability", capabilities);

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .addClaims(payload)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
