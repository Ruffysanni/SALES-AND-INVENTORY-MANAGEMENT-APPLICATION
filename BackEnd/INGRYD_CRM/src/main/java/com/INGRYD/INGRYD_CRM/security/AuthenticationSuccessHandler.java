package com.INGRYD.INGRYD_CRM.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

    @Component
    public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdmin = authorities.stream()
                    .anyMatch(g -> g.getAuthority().equals("ROLE_ADMIN"));
            boolean isSalesRep = authorities.stream()
                    .anyMatch(g -> g.getAuthority().equals("ROLE_SALES_REP"));
            boolean isCustomer = authorities.stream()
                    .anyMatch(g -> g.getAuthority().equals("ROLE_CUSTOMER"));
            boolean isSalesManager = authorities.stream()
                    .anyMatch(g -> g.getAuthority().equals("ROLE_SALES_MANAGER"));
            if (isAdmin) {
                response.sendRedirect("/admin/home");
            }else if (isCustomer) {
                response.sendRedirect("/customer/home");
            }else if (isSalesRep) {
                response.sendRedirect("/salesRep/home");
            }else {
                response.sendRedirect("/salesManager/home");
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
