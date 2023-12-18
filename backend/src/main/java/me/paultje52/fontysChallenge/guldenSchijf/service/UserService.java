package me.paultje52.fontysChallenge.guldenSchijf.service;
import me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth.LoginBodyDTO;
import me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth.LoginDTO;
import me.paultje52.fontysChallenge.guldenSchijf.model.DTO.auth.RegisterBodyDTO;
import me.paultje52.fontysChallenge.guldenSchijf.model.UserModel;
import me.paultje52.fontysChallenge.guldenSchijf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean registerUser(RegisterBodyDTO data) {
        Optional<UserModel> existingUser = this.userRepository.findByUsername(data.getUserName());
        if (existingUser.isPresent()) return false;

        String hashedPassword = encoder().encode(data.getPassword());
        this.userRepository.addUser(data.getUserName(),hashedPassword);

        return true;
    }

    public LoginDTO loginUser(LoginBodyDTO data) {
        Optional<UserModel> user = this.userRepository.findByUsername(data.getUserName());
        if (user.isEmpty()) return null;

        boolean matches = encoder().matches(data.getPassword(), user.get().getPassword());
        if (!matches) return null;

        String jwt = jwtService.generate(
                Integer.toString(user.get().getId())
        );

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setJwt(jwt);
        loginDTO.setName(user.get().getUsername());
        return loginDTO;
    }

    public String getUserName(int userId) {
        Optional<UserModel> user = this.userRepository.findById(userId);
        return user.map(UserModel::getUsername).orElse(null);
    }
}