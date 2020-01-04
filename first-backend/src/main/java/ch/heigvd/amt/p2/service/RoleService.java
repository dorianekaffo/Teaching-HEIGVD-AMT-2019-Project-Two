package ch.heigvd.amt.p2.service;

import ch.heigvd.amt.p2.model.Role;
import ch.heigvd.amt.p2.enums.RoleType;
import ch.heigvd.amt.p2.exception.ResourceNotFoundException;
import ch.heigvd.amt.p2.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IEntityService<Role, Long> {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role create(Role entity) {
        return this.roleRepository.save(entity);
    }

    @Override
    public Role update(Long id, Role entity) throws ResourceNotFoundException {
        if (this.roleRepository.existsById(id) && entity.getId() != null) {
            return this.roleRepository.save(entity);
        }
        throw new ResourceNotFoundException("Role", "id", id);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (this.roleRepository.existsById(id)) {
            this.roleRepository.deleteById(id);
        }
        throw new ResourceNotFoundException("Role", "id", id);
    }

    @Override
    public Role get(Long id) throws ResourceNotFoundException {
        return this.roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
    }

    @Override
    public Page<Role> get(Pageable pgble) {
        return this.roleRepository.findAll(pgble);
    }

    public boolean isAdmin(String ownerId) {
        return this.roleRepository.existsByTypeAndOwnerEmail(RoleType.ADMIN, ownerId);
    }
}
