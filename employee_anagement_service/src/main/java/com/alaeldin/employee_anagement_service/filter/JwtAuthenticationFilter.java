package com.alaeldin.employee_anagement_service.filter;

import com.alaeldin.employee_anagement_service.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }
        try
        {
           String token = header.substring(7);
           String username = jwtService.extractUserName(token);
           if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
           {
               UserDetails userDetails = userDetailsService.loadUserByUsername(username);
               if (jwtService.validateToken(token , userDetails))
               {
                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                           = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               }
           }
        }
        catch (ExpiredJwtException e)
        {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        catch (JwtException e)
        {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }
}
