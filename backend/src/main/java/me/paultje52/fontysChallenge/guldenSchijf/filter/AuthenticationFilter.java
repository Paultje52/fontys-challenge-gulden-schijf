package me.paultje52.fontysChallenge.guldenSchijf.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.paultje52.fontysChallenge.guldenSchijf.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@Component
@Order(1)
@CrossOrigin
public class AuthenticationFilter implements Filter {
    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (this.isProtectedEndpoint(req.getRequestURI().toLowerCase()) && !req.getMethod().equalsIgnoreCase("OPTIONS")) {
            String jwt = req.getHeader("Authorization");

            boolean isValid = this.jwtService.verify(jwt);
            if (!isValid) {
                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(401);
                res.setHeader("Content-Type", "application/json");
                res.getWriter().write("{\"message\":\"Authentication needed for this endpoint. Use /auth/login\"}");
                res.getWriter().flush();
                return;
            }

            String userId = this.jwtService.getUserId(jwt);
            req.setAttribute("userId", userId);
        }

        chain.doFilter(request, response);
    }

    private boolean isProtectedEndpoint(String uri) {
        return uri.equalsIgnoreCase("/auth/renew") ||
                (!uri.startsWith("/auth") &&
                   !uri.startsWith("/swagger") &&
                   !uri.startsWith("/v3") &&
                   !uri.equalsIgnoreCase("/docs.html") &&
                   !uri.equalsIgnoreCase("/")
                );
    }
}
