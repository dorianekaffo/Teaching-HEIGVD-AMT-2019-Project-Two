package ch.heigvd.p2.firstapi.service;

import ch.heigvd.p2.firstapi.enums.RoleType;
import ch.heigvd.p2.firstapi.exception.RessourceNotFoundException;
import ch.heigvd.p2.firstapi.model.Role;
import ch.heigvd.p2.firstapi.repository.IRoleRepository;
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
    public Role update(Long id, Role entity) throws RessourceNotFoundException {
        if (this.roleRepository.existsById(id) && entity.getId() != null) {
            return this.roleRepository.save(entity);
        }
        throw new RessourceNotFoundException("Role", "id", id);
    }

    @Override
    public void delete(Long id) throws RessourceNotFoundException {
        if (this.roleRepository.existsById(id)) {
            this.roleRepository.deleteById(id);
        }
        throw new RessourceNotFoundException("Role", "id", id);
    }

    @Override
    public Role get(Long id) throws RessourceNotFoundException {
        return this.roleRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Role", "id", id));
    }

    @Override
    public Page<Role> get(Pageable pgble) {
        return this.roleRepository.findAll(pgble);
    }

    public boolean isAdmin(String ownerId) {
        return this.roleRepository.existsByTypeAndOwnerEmail(RoleType.ADMIN, ownerId);
    }
}
