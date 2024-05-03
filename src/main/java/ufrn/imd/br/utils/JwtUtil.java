package ufrn.imd.br.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public Map<String, Claim> getAllClaimsFromToken(String token){
        return JWT.require(Algorithm.HMAC384(Base64.getUrlDecoder().decode(secret)))
                .build().verify(token).getClaims();
    }

    private boolean isTokenExpired(String token) throws Exception{
        return JWT.require(Algorithm.HMAC384(Base64.getUrlDecoder().decode(secret)))
                .build().verify(token).getExpiresAt().before(new Date());
    }


    public boolean isInvalid(String token) throws Exception{
        return this.isTokenExpired(token);
    }
}
