package jp.co.axa.apidemo.services;

import java.util.List;

import jp.co.axa.apidemo.entities.Role;

public interface RoleService {

	public List<Role> retrieveAllRoles();

    public Role getRoleById(Long RoleId);
    
    public Role saveRole(Role Role);

    public void deleteRole(Long RoleId);

    public Role updateRole(Role role, Long id);
    
    
}
