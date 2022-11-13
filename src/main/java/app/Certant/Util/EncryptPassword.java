
package app.Certant.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptPassword {
    public static void main(String[] args) {
        
        var password = "123";
        System.out.println("password : "+password+"--Encriptado : "+encriptarPassword(password));
    }
    
    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
