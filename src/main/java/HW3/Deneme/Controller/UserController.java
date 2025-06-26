package HW3.Deneme.Controller;

import HW3.Deneme.Dto.DtoConverter;
import HW3.Deneme.Dto.UserResponse;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final IUserService usersService;
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable) {
        Page<User> users = usersService.getAllUsers(pageable);
        Page<UserResponse> response = users.map(DtoConverter::toDto);
        return ResponseEntity.ok(response);
    }
}
