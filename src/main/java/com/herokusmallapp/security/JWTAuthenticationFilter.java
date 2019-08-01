package com.herokusmallapp.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.herokusmallapp.entity.LoggedUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private Gson gson = new Gson();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoggedUser loggedUser = new ObjectMapper().readValue(request.getInputStream(), LoggedUser.class);
            List<GrantedAuthority> authorities = loggedUser.getRoles().stream().map(role ->
                    new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

            Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loggedUser.getUsername(),
                    loggedUser.getPassword(),
                    authorities
            ));
            return authenticate;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        String[] roles = auth.getAuthorities().stream().map(authData -> ((GrantedAuthority) authData).getAuthority()).toArray(String[]::new);

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)) //new Date(System.currentTimeMillis() + (5 * 60 * 1000)) new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)
                .withArrayClaim("role", roles)
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        loadResponseBody(res,roles);
    }

    private void loadResponseBody(HttpServletResponse response, String[] roles) {

        String employeeJsonString = this.gson.toJson(roles);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(employeeJsonString);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error IO Respond");
        }
    }
}
