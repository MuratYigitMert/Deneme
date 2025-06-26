package HW3.Deneme.Service;

import HW3.Deneme.Dto.LoginRequest;
import HW3.Deneme.Dto.LoginResponse;
import HW3.Deneme.Dto.UserRegisterRequest;
import HW3.Deneme.Entity.User;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
    User register(UserRegisterRequest request);
}
