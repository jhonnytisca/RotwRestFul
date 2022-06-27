/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static String secretKey2 = "1234567890qwertyuiop:ROTW";
    private static String salt = "{\"idFacebook\":\"1406595209444895\",\"correoFacebook\":\"o0ox_xo0o@live.com\"}";

    public static void setKey(String myKey) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        try {
            key = myKey.getBytes("UTF-8");
            md5 = MessageDigest.getInstance("SHA-1");
            key = md5.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

//    public static String encrypt(String strToEncrypt, String secret) {
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, strToEncrypt.getBytes("UTF-8"), secret.getBytes("UTF-8"), md5);
//            SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
////            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
////            cipher.init(Cipher.ENCRYPT_MODE, key);
////            //return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
////            return new String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")), StandardCharsets.UTF_8);
//            IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);
//            Cipher cypher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cypher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] encoded = cypher.doFinal(strToEncrypt.getBytes());
////            return Base64.getEncoder().encodeToString(cypher.doFinal(strToEncrypt.getBytes("UTF-8")));
//            return new BASE64Encoder().encode(encoded);
//        } catch (Exception e) {
//            System.out.println("Error while encrypting: " + e.toString());
//        }
//        return null;
//    }
    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
        //Get Cipher Instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        //Create SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

        //Create IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(IV);

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plaintext);

        return cipherText;
    }

    public static String encrypt(String strToEncrypt, String secret, Integer algo) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey2.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {

            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String encrypt(String plainText) {
        String encryptionKey = "1234567890qwerty";
        String cipherTransformation = "AES/CBC/PKCS5PADDING";
        String characterEncoding = "UTF-8";
        String aesEncryptionAlgorithem = "AES";
        String encryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
            System.err.println("Encrypt Exception : " + E.getMessage());
        }
        return encryptedText;
    }
//
//    public static String decrypt(String strToDecrypt, String secret) {
//        try {
//            setKey(secret);
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey);
//            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//        } catch (Exception e) {
//            System.out.println("Error while decrypting: " + e.toString());
//        }
//        return null;
//    }

    public static String decryptV2(String cadena) throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        byte[] cipherData = Base64.getDecoder().decode(cadena);
        byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secretKey2.getBytes("UTF-8"), md5);
        SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
        System.out.println("KEY SIZE :: " + key.getEncoded().length);
        IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

        byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
        Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedData = aesCBC.doFinal(encrypted);
        String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
        return decryptedText;
    }

    public static String encryptV2(String cadena, String secret) throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException,
            NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeySpecException {

        try {
            byte[] cipherData = cadena.getBytes("UTF-8");
            byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
            
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes("UTF-8"), md5);
            IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(),saltData, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return Base64.getEncoder().encodeToString(cipher.doFinal(cadena.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

        int digestLength = md.getDigestLength();
        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();

            // Repeat process until sufficient data has been generated
            while (generatedLength < keyLength + ivLength) {

                // Digest data (last digest if available, password data, salt if available)
                if (generatedLength > 0) {
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                }
                md.update(password);
                if (salt != null) {
                    md.update(salt, 0, 8);
                }
                md.digest(generatedData, generatedLength, digestLength);

                // additional rounds
                for (int i = 1; i < iterations; i++) {
                    md.update(generatedData, generatedLength, digestLength);
                    md.digest(generatedData, generatedLength, digestLength);
                }

                generatedLength += digestLength;
            }

            // Copy key and IV into separate byte arrays
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
            if (ivLength > 0) {
                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);
            }

            return result;

        } catch (DigestException e) {
            throw new RuntimeException(e);

        } finally {
            // Clean out temporary data
            Arrays.fill(generatedData, (byte) 0);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        String json = "{\\\"idFacebook\\\":\\\"1406595209444895\\\",\\\"correoFacebook\\\":\\\"o0ox_xo0o@live.com\\\"}";
        String jsonEnc = "U2FsdGVkX1/vGIX0iJtCtXhgy66N4sSBzp17+8wFcuC2W9p5X1sxMw6QahAU1kC+i4iB53XXKeGwnIjOKzSP2Q9GXwplBBFWcIRddLwBz/Jzd3PkeQtQ91eDQgGoiOExcM0crnYt806+H6toqpSXvQjlfbWGh5tfCgNBKBgLFvm2Y3sWanVrMvn7Hb5UXmI/";
        String encyptStr = decryptV2(jsonEnc);
        System.out.println(encyptStr);
    }
}
