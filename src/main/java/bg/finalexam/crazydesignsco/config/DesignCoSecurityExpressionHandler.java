package bg.finalexam.crazydesignsco.config;

import bg.finalexam.crazydesignsco.service.impl.DesignServiceImpl;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class DesignCoSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final DesignServiceImpl designServiceImpl;

    public DesignCoSecurityExpressionHandler(DesignServiceImpl designServiceImpl) {
        this.designServiceImpl = designServiceImpl;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {

        OwnerSecurityExpressionRoot root = new
                OwnerSecurityExpressionRoot(authentication, designServiceImpl);

        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
