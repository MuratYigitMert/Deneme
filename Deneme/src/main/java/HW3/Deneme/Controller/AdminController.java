package HW3.Deneme.Controller;


import HW3.Deneme.Dto.RoleChangeRequest;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class AdminController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/change-role")
    public ResponseEntity<?> changeUserRole(@RequestBody RoleChangeRequest request) {
        System.out.println("Inside changeUserRole...");
        System.out.println("Security Context Auth: " + SecurityContextHolder.getContext().getAuthentication());
        User updated = userService.changeUserRole(request.getUserId(), request.getRoleName());
        return ResponseEntity.ok("User role updated to " + updated.getRole().getName());
    }
}
