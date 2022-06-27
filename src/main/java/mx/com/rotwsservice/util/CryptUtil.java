/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author Marco Villa
 */
public class CryptUtil {
    public String desencriptarCadena(String key, String initVector, String encrypted){
        try{
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        }catch (java.io.IOException e) {
            //Log.printError("ShaHashingUtil:desencriptarCadena(Utileria)", e.getCause());
        }catch(Exception ex){
            //Log.printError("ShaHashingUtil:desencriptarCadena(Utileria)", ex.getCause());
        }
        return null;
    }
}
