package HW3.Deneme.Service;

import HW3.Deneme.Entity.Role;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Repository.RoleRepo;
import HW3.Deneme.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public User changeUserRole(int userID, String role) {
        User user = userRepo.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role newRoleName= roleRepo.findByName(role);
        user.setRole(newRoleName);
        return  userRepo.save(user);
    }
    public User getUserById(int userID) {
        return userRepo.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
