package me.paultje52.fontysChallenge.guldenSchijf.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth.*;
import me.paultje52.fontysChallenge.guldenSchijf.service.JwtService;
import me.paultje52.fontysChallenge.guldenSchijf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for Authentication")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public AuthResponseDTO registerEndpoint(@RequestBody RegisterBodyDTO data) {
        boolean registerSuccess = userService.registerUser(data);
        if (!registerSuccess) return new AuthResponseDTO(false);

        LoginBodyDTO loginDTO = new LoginBodyDTO();
        loginDTO.setUserName(data.getUserName());
        loginDTO.setPassword(data.getPassword());

        LoginDTO login = userService.loginUser(loginDTO);
        return login == null
                ? new AuthResponseDTO(false)
                : new AuthResponseDTO(true, login.getJwt(), login.getName());
    }

    @PostMapping("/login")
    public AuthResponseDTO loginEndpoint(@RequestBody LoginBodyDTO data) {
        LoginDTO login = userService.loginUser(data);
        return login == null
                ? new AuthResponseDTO(false)
                : new AuthResponseDTO(true, login.getJwt(), login.getName());
    }

    @PostMapping("/renew")
    public RenewResponseDTO renewEndpoint() {
        String jwt = this.jwtService.generate();
        return new RenewResponseDTO(true, jwt);
    }
}
