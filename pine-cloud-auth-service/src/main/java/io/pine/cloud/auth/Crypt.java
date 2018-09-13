package io.pine.cloud.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * Crypt库
 *
 * @author liujun
 * @sinace 2017/12/12 0012.
 */
public final class Crypt {
    // secrypt keys from ucenter
    public final static String UCENTER_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM3nFhgJEklXunLviyu86zg4N8knZx+1lj1416n2oWWzgPi/925HQNmj3lUr7linQ56TIttpiLXqhvPgMFrzkCn9JFxKKNa1YLRnXiEB+amxHarC97cm1/vZKi4299cWE6elzGEdE30Z40Pp2WrRCTjQrrUIXv3AtBtU4QlFd+ynAgMBAAECgYAMapL6gBGKOrATnj5WNN46VamHh8jAMMMOkervZN/2sgxk866IB+PxO+B8YlnNHskFFvpTINoNnkblDe/cjDN1OPgMBQXzV7ciarXfoCClQMsTcyyEcq6KWMj16kSmJZNLkfHmz1tP4mig9hDCpgBTWeHMv+cqLZaHG4DDebSKAQJBAPhtRvHbuMhz9MVOlH+xGkLEOu07njW/SwhKl1AVHjt8AVBnyhW9Dg2nS+DDCoE0xTUvidqkJiLWnOqrGnIN/ZECQQDULfNVEHYsgVbVWHdkNY5NKXxisT1E2rJqDUbjF42ymJyqi2hOtt8hcq6IdSn6pj4Wf8aIrsnVyX9R3N2QkAq3AkEAvstGimgC0cjku0hvmmZNUevdieeYyZSiLrsC3Rlq/6psY/FibjGlgmzzRKrm/Xam4wMgr9AUDfufxkck52XBUQJAflN5W7z4TtkWxODt/HiyZ5h6mRvjWZpHvUJ4YP6F+75HEHb9vFe+k2B/L3lqa/QMZULgBkLiomlcZ3XzaH2KEwJAXj7qEpV7zThfab2w/5LwRuFe5YDej0fBHWjYFa3BPGXRqXHnA8MknRBEfsO6iV87syGynn53S0WYgNsZvL3Zpw==";
    public final static String UCENTER_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDN5xYYCRJJV7py74srvOs4ODfJJ2cftZY9eNep9qFls4D4v/duR0DZo95VK+5Yp0OekyLbaYi16obz4DBa85Ap/SRcSijWtWC0Z14hAfmpsR2qwve3Jtf72SouNvfXFhOnpcxhHRN9GeND6dlq0Qk40K61CF79wLQbVOEJRXfspwIDAQAB";

    public static String sign(String privateKey) {
        JWTCreator.Builder jwtBuilder = JWT.create();
        jwtBuilder.withIssuer("ucenter");
        jwtBuilder.withSubject("1561fc6e1e1f44b38acc416e2de28602");
        jwtBuilder.withAudience("uc94b43010fc03188d", "ucenter");
        Instant now = Instant.now();
        // default expires after 1 day
        jwtBuilder.withExpiresAt(Date.from(now.plusSeconds(24*60*60)));
        jwtBuilder.withIssuedAt(Date.from(now));
        jwtBuilder.withJWTId(UUID.randomUUID().toString().replace("-", ""));
        jwtBuilder.withClaim("p_v", 1512092542000L);
        jwtBuilder.withClaim("ident_id", "623811a960124fd580226f968b116caa");
        jwtBuilder.withClaim("i_v", 1512092542000L);
        jwtBuilder.withClaim("type", 1);
//        byte[] keyBytes = Base64.decodeBase64(privateKey);
        byte[] keyBytes = java.util.Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try{
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateK = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
            return jwtBuilder.sign(Algorithm.RSA256( null, privateK));
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e){
            e.printStackTrace();
        }
        return "";
    }

    public static DecodedJWT verify(String token, String publicKey) {
//        byte[] keyBytes = Base64.decodeBase64(publicKey);
        byte[] keyBytes = java.util.Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey publicK = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
            Verification verifier = JWT.require(Algorithm.RSA256(publicK, null));
            verifier.withIssuer("ucenter")
                    .withAudience("uc94b43010fc03188d");
            return verifier.build().verify(token);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException e){
            e.printStackTrace();
        }
        return null;
    }
}
