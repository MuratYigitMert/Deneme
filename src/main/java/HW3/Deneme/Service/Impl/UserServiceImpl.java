package HW3.Deneme.Service.Impl;

import HW3.Deneme.Entity.Role;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Exception.ResourceNotFoundException;
import HW3.Deneme.Repository.RoleRepo;
import HW3.Deneme.Repository.UserRepo;
import HW3.Deneme.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public User changeUserRole(int userID, String role) {
        User user = userRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Role newRole = roleRepo.findByName(role);
        user.setRole(newRole);
        return userRepo.save(user);
    }

    @Override
    public User getUserById(int userID) {
        return userRepo.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
