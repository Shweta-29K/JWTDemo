package com.example.JWTDemo.util;

import com.example.JWTDemo.error.JWTAuthException;

import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;

public class KeyStoreUtils {
    private static KeyStore keyStore;
    private static KeyStore trustStore;

    private KeyStoreUtils(){}

    public static void loadKeyStore(final String fileName, final String password){
        try{
            if (keyStore == null){
                InputStream inputStream =
                        Thread.currentThread().
                                getContextClassLoader().
                                getResourceAsStream(fileName);
                keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(inputStream,password.toCharArray());
            }

        }catch (Exception e){
            throw new JWTAuthException(e);
        }
    }

    public static void loadTrustStore(final String fileName, final String password){
        try{
            if (trustStore == null){
                InputStream inputStream =
                        Thread.currentThread().
                                getContextClassLoader().
                                getResourceAsStream(fileName);
                trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(inputStream,password.toCharArray());
            }

        }catch (Exception e){
            throw new JWTAuthException(e);
        }
    }

    public static PrivateKey getPrivateKey(String alias, String password){
        PrivateKey key = null;
        try{
            KeyStore.PrivateKeyEntry privateKeyEntry =
                    (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias,new KeyStore.PasswordProtection(password.toCharArray()));
            key = privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            throw new JWTAuthException(e);
        }
        return key;
    }

    public static PublicKey getPublicKey(String alias){
        PublicKey key = null;
        try {
            Certificate certificate = trustStore.getCertificate(alias);
            key = certificate.getPublicKey();
        } catch (Exception e) {
            throw new JWTAuthException(e);
        }
        return  key;
    }

    public  static  void clear(){
        keyStore = null;
        trustStore = null;
    }

}
