package io.pine.cloud.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Auth Service Application
 */
public class App 
{
    public static void main( String[] args )
    {
        String token = Crypt.sign(Crypt.UCENTER_PRIVATE_KEY);
        System.out.println("signed token: " + token);

        //用户中心登录后的token解签
//        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxNTYxZmM2ZTFlMWY0NGIzOGFjYzQxNmUyZGUyODYwMiIsImF1ZCI6WyJ1Yzk0YjQzMDEwZmMwMzE4OGQiLCJ1Y2VudGVyIl0sImlfdiI6MTUxMjA5MjU0MjAwMCwiaWRlbnRfaWQiOiI2MjM4MTFhOTYwMTI0ZmQ1ODAyMjZmOTY4YjExNmNhYSIsInBfdiI6MTUxMjA5MjU0MjAwMCwiaXNzIjoidWNlbnRlciIsImV4cCI6MTUxMzEzNzQxOSwidHlwZSI6MSwiaWF0IjoxNTEzMDUxMDE5LCJqdGkiOiJhZTc5NjVjNzE2N2U0NTg2ODg3OWY4Y2E4ZWUxNDQ3NyJ9.Wg_fGf_NiL10tnMEQ8UBVyOHferXy5dRne3DEDwyDJcyXceUf2DbVblBOW5c2ac_JlQBv1SU2ylVLTN9fsA-ySSEaZ7OgPasXtq_ATZqQYT1_lskGPvPhasjOki8oxa22-JZpmRA20HGKWKIfN5Q2P1j2qLlxBQDgj1rMbWQrwU";
        DecodedJWT decoded = Crypt.verify(token, Crypt.UCENTER_PUBLIC_KEY);
        System.out.println("token verified with: ");
        System.out.println("decoded header: " + decoded.getHeader());
        System.out.println("decoded payload: " + decoded.getPayload());
        System.out.println("audience: " + decoded.getAudience());
    }
}
