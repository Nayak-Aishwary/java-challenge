package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Role;
import jp.co.axa.apidemo.services.RoleService;

/**
 * The RoleController class is a RESTful controller designed for handling
 * role-related operations. It provides endpoints for retrieving, creating,
 * updating, and deleting role information. This controller serves as the bridge
 * between the API and the service layer, enabling the management of role data.
 */
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * Retrieve a list of all roles.
	 * 
	 * @return List of roles.
	 */
	@GetMapping("/getAll")
	public List<Role> getAllRoles() {
		List<Role> roles = roleService.retrieveAllRoles();
		return roles;
	}

	/**
	 * Retrieve a role by its unique ID.
	 * 
	 * @param roleId The ID of the role to retrieve.
	 * @return The role with the specified ID.
	 */
	@GetMapping("/getById/{roleId}")
	public ResponseEntity<Role> getRoleById(@PathVariable(name = "roleId") Long roleId) {
		return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
	}

	/**
	 * Register a new role.
	 * 
	 * @param role The role to register.
	 * @return A ResponseEntity with the registered role and an HTTP status code.
	 */
	@PostMapping("/register")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.CREATED);
	}

	/**
	 * Delete a role by its ID.
	 * 
	 * @param roleId The ID of the role to delete.
	 * @return A ResponseEntity with a message confirming the deletion and an HTTP
	 *         status code.
	 */
	@DeleteMapping("/{roleId}")
	public ResponseEntity<String> deleteRole(@PathVariable(name = "roleId") Long roleId) {
		roleService.deleteRole(roleId);
		return ResponseEntity.ok("Role deleted successfully with role ID: " + roleId);
	}

	/**
	 * Update a role by its ID.
	 * 
	 * @param role   The updated role information.
	 * @param roleId The ID of the role to update.
	 * @return A ResponseEntity with the updated role and an HTTP status code.
	 */
	@PutMapping("/update/{roleId}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable(name = "roleId") Long roleId) {
		return new ResponseEntity<>(roleService.updateRole(role, roleId), HttpStatus.OK);
	}

}
