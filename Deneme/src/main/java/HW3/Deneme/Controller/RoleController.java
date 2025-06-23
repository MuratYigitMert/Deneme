package HW3.Deneme.Controller;

import HW3.Deneme.Entity.Role;
import HW3.Deneme.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")

public class RoleController {
    private final RoleService roleService;
    @GetMapping
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleService.getAllRoles(pageable);
    }
}
