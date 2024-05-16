package com.example.socialmediaapp.controllers;


import com.example.socialmediaapp.dto.AuthRequestDTO;
import com.example.socialmediaapp.dto.JwtResponseDTO;
import com.example.socialmediaapp.dto.RefreshTokenRequestDTO;
import com.example.socialmediaapp.dto.UserDTO;
import com.example.socialmediaapp.token.JwtService;
import com.example.socialmediaapp.services.RefreshTokenService;
import com.example.socialmediaapp.token.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    System.out.println(accessToken);
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @GetMapping
    public Object getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails userDetail) {
            return new UserDTO(userDetail.user());
        }
        return principal;
    }
//    private boolean verifyTwoFactorCode(User user, String twoFactorCode) {
//        if (user.getTwofa_code() == null || user.getTwofa_code().isEmpty()) {
//            return true;
//        } else {
//            String secretKey = user.getTwofa_code();
//            GoogleAuthenticator gAuth = new GoogleAuthenticator();
//            int code = Integer.parseInt(twoFactorCode);
//            return gAuth.authorize(secretKey, code);
//        }
//    }
}
