package jp.co.axa.apidemo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Role;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.RoleRepository;
import jp.co.axa.apidemo.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> retrieveAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(Long roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
	}

	@Override
	public Role saveRole(Role role) {
		roleRepository.save(role);
		return role;
	}

	@Override
	public void deleteRole(Long roleId) {
		roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
		roleRepository.deleteById(roleId);
	}

	@Override
	public Role updateRole(Role role, Long id) {
		Role existingRole = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "Id", id));
		existingRole.setName(role.getName());
		existingRole.setUsers(role.getUsers());
		roleRepository.save(existingRole);
		return existingRole;
	}
}
