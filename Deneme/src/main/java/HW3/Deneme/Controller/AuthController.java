package HW3.Deneme.Controller;

import HW3.Deneme.Dto.LoginRequest;
import HW3.Deneme.Dto.LoginResponse;
import HW3.Deneme.Dto.UserRegisterRequest;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserRegisterRequest request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}