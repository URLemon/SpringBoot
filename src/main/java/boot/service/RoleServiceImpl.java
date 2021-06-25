package boot.service;

import boot.model.Role;
import boot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository dao;

    public RoleServiceImpl(RoleRepository dao){
        this.dao = dao;
    }

    @Override
    @Transactional
    public Role getRoleByName(String nameRole) {
        return dao.findByRole(nameRole);
    }
}

