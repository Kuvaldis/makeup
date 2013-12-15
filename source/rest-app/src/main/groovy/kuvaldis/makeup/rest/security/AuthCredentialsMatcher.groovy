package kuvaldis.makeup.rest.security

import com.google.inject.Inject
import kuvaldis.makeup.lib.annotation.PasswordEncrypt
import kuvaldis.makeup.lib.service.EncryptionService
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.credential.CredentialsMatcher

/**
 * @author Kuvaldis
 * Create date: 15.12.13 18:05
 */
class AuthCredentialsMatcher implements CredentialsMatcher {

    @Inject @PasswordEncrypt
    private EncryptionService encryptionService

    @Override
    boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        encryptionService.encrypt(token.getCredentials() as String) == info.getCredentials()
    }
}
