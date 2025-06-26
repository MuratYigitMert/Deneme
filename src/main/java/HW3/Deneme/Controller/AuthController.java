package HW3.Deneme.Controller;

import HW3.Deneme.Dto.LoginRequest;
import HW3.Deneme.Dto.LoginResponse;
import HW3.Deneme.Dto.RegisterResponse;
import HW3.Deneme.Dto.UserRegisterRequest;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody UserRegisterRequest request) {
        User user = iAuthService.register(request);
        RegisterResponse response = new RegisterResponse();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setRoleId(user.getRole().getId());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(iAuthService.login(loginRequest));
    }
}