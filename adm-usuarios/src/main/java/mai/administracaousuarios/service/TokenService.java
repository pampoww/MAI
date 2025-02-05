package mai.administracaousuarios.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import mai.administracaousuarios.model.Usuario;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenService {

    private final String secret = System.getenv("SECRET");

    public String createToken(Usuario usuario) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-mai")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

        }catch (JWTCreationException e){
            e.printStackTrace();
            return null;
        }
    }

    public String verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth-mai")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }

}
