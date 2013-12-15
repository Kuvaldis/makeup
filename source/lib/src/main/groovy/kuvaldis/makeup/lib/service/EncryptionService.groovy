package kuvaldis.makeup.lib.service
/**
 * @author Kuvaldis
 * Create date: 15.12.13 16:57
 */
interface EncryptionService {
    String encrypt(String strToEncrypt)
    String decrypt(String strToDecrypt)
}
