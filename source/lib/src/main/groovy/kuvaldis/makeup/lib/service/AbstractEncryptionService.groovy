package kuvaldis.makeup.lib.service

import org.apache.commons.codec.binary.Base64

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * @author Kuvaldis
 * Create date: 15.12.13 16:34
 */
abstract class AbstractEncryptionService implements EncryptionService {

    @Override
    String encrypt(String strToEncrypt) {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        final SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
        return encryptedString;
    }

    @Override
    String decrypt(String strToDecrypt) {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        final SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
        return decryptedString;
    }

    abstract byte[] getSecretKey()
}
