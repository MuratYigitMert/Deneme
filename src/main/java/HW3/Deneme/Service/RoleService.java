package HW3.Deneme.Service;


import HW3.Deneme.Entity.Role;
import HW3.Deneme.Repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }
}
