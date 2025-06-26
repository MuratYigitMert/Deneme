package HW3.Deneme.Service;

import HW3.Deneme.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> getAllUsers(Pageable pageable);
    User changeUserRole(int userID, String role);
    User getUserById(int userID);
}
