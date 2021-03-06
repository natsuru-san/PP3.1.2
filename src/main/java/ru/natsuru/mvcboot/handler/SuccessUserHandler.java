package ru.natsuru.mvcboot.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        check: {
            if (roles.contains("ROLE_ADMIN")) {
                httpServletResponse.sendRedirect("/admin");
                break check;
            }
            if (roles.contains("ROLE_USER")) {
                httpServletResponse.sendRedirect("/user");
                break check;
            }
            httpServletResponse.sendRedirect("/login");
        }
    }
}
