package com.example.paymentsystem.filter;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class RoleBasedAuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) httpRequest.getUserPrincipal();
        if (principal != null) {
            KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
            Set<String> roles = keycloakSecurityContext.getToken().getRealmAccess().getRoles();

            String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
            if (path.startsWith("/admin")) {
                if (roles.contains("admin")) {
                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                }
            } else if (path.startsWith("/clients") || path.startsWith("/creditcards") || path.startsWith("/accounts")
                    || path.startsWith("/payments")) {
                if (roles.contains("user") || roles.contains("admin")) {
                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
