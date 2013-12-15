package kuvaldis.makeup.lib.service

import com.google.inject.Inject
import com.google.inject.name.Named

/**
 * @author Kuvaldis
 * Create date: 15.12.13 16:48
 */
@com.google.inject.Singleton
class PasswordEncryptionService extends AbstractEncryptionService {

    private byte[] key

    @Inject
    PasswordEncryptService(@Named('db.encryption.key.password') String strKey) {
        key = strKey.bytes
    }

    @Override
    byte[] getSecretKey() {
        return key
    }
}
