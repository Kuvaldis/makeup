package kuvaldis.makeup.rest.security

import com.google.inject.Inject
import kuvaldis.makeup.lib.service.UserService
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.credential.CredentialsMatcher
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection

/**
 * @author Kuvaldis
 * Create date: 15.12.13 17:44
 */
@com.google.inject.Singleton
class AuthRealm extends AuthorizingRealm {

    @Inject
    UserService userService

    @Inject
    AuthRealm(CredentialsMatcher matcher) {
        super(matcher)
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

    }
}
