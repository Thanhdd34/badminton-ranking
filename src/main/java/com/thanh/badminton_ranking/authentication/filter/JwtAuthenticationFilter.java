
package com.thanh.badminton_ranking.authentication.filter;

import com.thanh.badminton_ranking.authentication.service.JwtService;
import com.thanh.badminton_ranking.user.entity.User;
import com.thanh.badminton_ranking.user.repository.UserRepository;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader =
                request.getHeader("Authorization");

        if (authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);

        try {
            String username = jwtService.extractUsername(token);

            if (username != null
                    && SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

                User user = userRepository.findByUsername(username)
                        .orElse(null);

                if (user != null
                        && Boolean.TRUE.equals(user.getEnabled())
                        && jwtService.validateToken(token, user)) {

                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority(
                                    "ROLE_" + user.getRole().name()
                            );

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    List.of(authority)
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        } catch (JwtException | IllegalArgumentException e) {
            // Token không hợp lệ: không thiết lập Authentication.
            // Spring Security sẽ xử lý quyền truy cập ở bước tiếp theo.
        }

        filterChain.doFilter(request, response);
    }
}
