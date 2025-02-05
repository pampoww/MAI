package mai.administracaousuarios.project.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.ArrayList;

public class Encrypt {
    public static String[] encryptPassword(String password){
        String salt = KeyGenerators.string().generateKey();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPass = encoder.encode(password + salt);

        return new String[]{encryptedPass, salt};
    }

    public static Boolean validatePassword(String sentPassword, String salt, String originalPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(sentPassword + salt, originalPassword);
    }
}
