package com.awa.centrale.gestioncompte.service.role;

import com.awa.centrale.gestioncompte.dao.UtilisateurDao;
import com.awa.centrale.gestioncompte.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListerRolesService {

    @Autowired
    UtilisateurDao utilisateurDao;

    public List<Role> listerRoles() {
        return utilisateurDao.listerRoles();
    }
}
