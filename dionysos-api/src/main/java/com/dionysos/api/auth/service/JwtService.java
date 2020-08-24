package com.dionysos.api.auth.service;

import com.dionysos.api.common.response.code.DionysosAPIErrorCode;
import com.dionysos.api.auth.exception.UnAuthorizedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final String HEADER_AUTH = "Authorization";

    public String create(String uid) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,
                30
        );

        Key key = Keys.hmacShaKeyFor(generateKey());
        Claims claims = Jwts.claims()
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(cal.getTimeInMillis()));

        claims.put("uid", uid);

        String jws = Jwts.builder()
                .setHeaderParam("typ",
                        "JWT"
                ).setClaims(claims)
                .signWith(key,
                        SignatureAlgorithm.HS256
                ).compact();

        return jws;
    }

    public boolean ValidateUser(String jws) {
        log.info("validateUser jws : {} ",
                jws
        );
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

        return jwsClaims.getBody()
                .get(key)
                .toString();
    }

    private Jws<Claims> getJwsClaims(String jws) {
        Jws<Claims> claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(jws);
        } catch (ExpiredJwtException e) {
            log.error("Token expired : " + jws);
            throw new UnAuthorizedException(DionysosAPIErrorCode.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new UnAuthorizedException(DionysosAPIErrorCode.UNAUTHORIZATION);
        }

        return claims;
    }

    private byte[] generateKey() {
        byte[] key = null;

        try {
            key = secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("generate key fail : {}",
                    e
            );
        }

        return key;
    }

}
