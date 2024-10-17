package com.crud.api.controllers;

import com.crud.api.models.UserModel;
import com.crud.api.repositores.UserRepository;
import com.crud.api.services.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody Map<String, String> datos) {
        String user = datos.get("firstname");//Acá le mando el user según lo que manden en los datos
        String pass = datos.get("password");

        try {
            //Revisar si está autentificado
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));

            //Revisar si existe en la DB
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(user);
            UserModel userModel = userRepository.getByName(user);

            //Generar token y refreshtoken
            String jwtToken = this.jwtUtilService.generateToken(userDetails, userModel.getRol());
            String jwtRefreshToken = this.jwtUtilService.generateRefreshToken(userDetails, userModel.getRol());

            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", jwtToken);
            tokenMap.put("refreshToken", jwtRefreshToken);

            return ResponseEntity.ok(tokenMap);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de auntenticación:" + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        try {

            String username = jwtUtilService.extractUserName(refreshToken);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UserModel userModel = userRepository.getByName(username);

            if (jwtUtilService.validateToken(refreshToken, userDetails)) {

                String newJwt = jwtUtilService.generateToken(userDetails, userModel.getRol());
                String newRefreshJwt = jwtUtilService.generateRefreshToken(userDetails, userModel.getRol());

                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", newJwt);
                tokenMap.put("refreshToken", newRefreshJwt);

                return ResponseEntity.ok(tokenMap);

            } else {

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid RefreshToken");

            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de refresh token:" + e.getMessage());
        }
    }
}
