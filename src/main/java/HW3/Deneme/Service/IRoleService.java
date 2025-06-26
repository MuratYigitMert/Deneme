package HW3.Deneme.Service;

import HW3.Deneme.Entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {
    Page<Role> getAllRoles(Pageable pageable);
}
