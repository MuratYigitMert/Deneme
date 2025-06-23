package HW3.Deneme.Service;


import HW3.Deneme.Auth.JwtUtil;
import HW3.Deneme.Dto.LoginRequest;
import HW3.Deneme.Dto.LoginResponse;
import HW3.Deneme.Dto.UserRegisterRequest;
import HW3.Deneme.Entity.Role;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Repository.RoleRepo;
import HW3.Deneme.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public LoginResponse login(LoginRequest loginRequest){
        try {

                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    );


            User user = userRepo.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.createToken(user);
            return new LoginResponse(token, user.getUsername(), user.getEmail());

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username/password");
        }

    }


    public User register(UserRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        Role role= roleRepo.findByName("user");
        user.setRole(role);
        return userRepo.save(user);
    }
}
