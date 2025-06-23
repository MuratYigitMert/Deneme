package HW3.Deneme.Controller;

import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService usersService;
    @GetMapping
    public Page<User> getAllUsers(Pageable pageable) {
        return usersService.getAllUsers(pageable);
    }
}
