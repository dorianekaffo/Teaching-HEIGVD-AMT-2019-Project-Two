package com.heigvd.p2.secondapi.service;

import com.heigvd.p2.secondapi.model.Group;
import com.heigvd.p2.secondapi.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceImpl implements EntityService<Group> {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group create(Group entity) {
        return this.groupRepository.save(entity);
    }

    @Override
    public Group update(Long id, Group entity) {
        if (this.groupRepository.existsById(id) && entity.getId() != null) {
            return this.groupRepository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        this.groupRepository.deleteById(id);
    }

    @Override
    public Group get(Long id) {
        return this.groupRepository.findById(id).get();
    }
}
