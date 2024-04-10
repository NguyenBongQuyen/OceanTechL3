package com.octl3.api.security;

import com.octl3.api.commons.exceptions.ErrorMessages;
import com.octl3.api.commons.exceptions.OctException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.octl3.api.constants.SecurityConst.API_FORBIDDEN;

@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
//        log.info(String.valueOf(httpServletResponse.getStatus()));

        httpServletResponse.sendRedirect(API_FORBIDDEN);
//        if (httpServletResponse.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
//            throw new OctException(ErrorMessages.UNAUTHORIZED);
//        } else if (httpServletResponse.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
//            throw new OctException(ErrorMessages.FORBIDDEN);
//        }

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            // Set status code 401 (UNAUTHORIZED)
//            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            // Write custom response message for UNAUTHORIZED
//            httpServletResponse.getWriter().write("Unauthorized: You need to login to access this resource.");
//        } else {
//            // Set status code 403 (FORBIDDEN)
//            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            // Write custom response message for FORBIDDEN
//            httpServletResponse.getWriter().write("Forbidden: You don't have permission to access this resource.");
//        }
    }
}