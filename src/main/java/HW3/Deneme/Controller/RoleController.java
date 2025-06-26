package HW3.Deneme.Controller;

import HW3.Deneme.Dto.DtoConverter;
import HW3.Deneme.Dto.RoleResponse;
import HW3.Deneme.Entity.Role;
import HW3.Deneme.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")

public class RoleController {
    private final RoleService roleService;
    @GetMapping
    public ResponseEntity<Page<RoleResponse>> getAllRoles(Pageable pageable) {
        Page<Role> roles = roleService.getAllRoles(pageable);
        Page<RoleResponse> response = roles.map(DtoConverter::toDto);
        return ResponseEntity.ok(response);
    }
}
