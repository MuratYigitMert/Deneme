package HW3.Deneme.Service.Impl;

import HW3.Deneme.Entity.Role;
import HW3.Deneme.Repository.RoleRepo;
import HW3.Deneme.Service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepo roleRepo;

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }
}