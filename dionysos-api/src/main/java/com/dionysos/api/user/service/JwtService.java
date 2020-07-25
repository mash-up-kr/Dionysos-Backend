package com.dionysos.api.user.service;

import com.dionysos.api.exception.UnAuthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Key;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final String HEADER_AUTH = "Authorization";

    public String create(String uid) {
        Key key = Keys.hmacShaKeyFor(generateKey());

        String jws = Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setSubject("user")
                        .claim("uid", uid)
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact();

        return jws;
    }

    public boolean ValidateUser(String jws) {
        log.info("validateUser jws : {} ", jws);
        getJwsClaims(jws);

        return true;
    }

    public String getUid() {
        return getValue("uid");
    }

    private String getValue(String key) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        String jws = httpServletRequest.getHeader(HEADER_AUTH);

        Jws<Claims> jwsClaims = getJwsClaims(jws);

        return jwsClaims.getBody().get(key).toString();
    }

    private Jws<Claims> getJwsClaims(String jws) {
        Jws<Claims> claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(jws);
        } catch (Exception e) {
            throw new UnAuthorizedException("올바르지 않은 계정입니다.");
        }

        return claims;
    }

    private byte[] generateKey() {
        byte[] key = null;

        try {
            key = secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("generate key fail : {}", e);
        }

        return key;
    }

}
