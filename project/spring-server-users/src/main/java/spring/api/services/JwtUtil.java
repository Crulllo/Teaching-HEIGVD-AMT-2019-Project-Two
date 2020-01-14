package spring.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import spring.configuration.JwtConfig;

import java.util.Date;

/**
 * Utility class for generating jwt tokens, verifying or decoding them.
 * Used as a Component in the Spring Application, but could also be refactored as a static class.
 */
@Component
public class JwtUtil {

    private final static int TOKEN_DURATION = 30 * 100000;

    public String createToken(String username, boolean isAdmin) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.JWT_SHARED_SECRET.getBytes());
            token = JWT.create()
                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                    .withIssuer("Me")
                    .withSubject(username)
                    .withClaim("isAdmin", isAdmin)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new Error("Could not generate JWT token");
        }
    }

    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(JwtConfig.JWT_SHARED_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Me")
                .build(); //Reusable verifier instance
        return verifier.verify(token);
    }

    /**
     * Decode jwt token. This method should be used only after the token has been verified.
     * @param token jwt token
     * @return decoded jwt token
     */
    public DecodedJWT decodeToken(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException exception){
            //Invalid token
            throw new Error("Could not decode token");
        }
    }

    /**
     * Extracts the token from the authorization header by removing the Bearer keyword.
     * @param authHeader the authorization header containing the jwt token preceded by Bearer
     * @return the extracted token
     * @throws JWTVerificationException
     */
    public String extractToken(String authHeader) {
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        } else {
            throw new JWTVerificationException("Malformed authorization header: Token must start with Bearer " +
                    "followed by blank space");
        }
    }
}
