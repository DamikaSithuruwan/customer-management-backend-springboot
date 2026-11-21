package lk.brightenacademy.customer_demo.controller;

import lk.brightenacademy.customer_demo.dto.*;
import lk.brightenacademy.customer_demo.entity.Permission;
import lk.brightenacademy.customer_demo.entity.Role;
import lk.brightenacademy.customer_demo.entity.User;
import lk.brightenacademy.customer_demo.repository.PermissionRepository;
import lk.brightenacademy.customer_demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDataDTO add(@RequestBody RoleInsertDTO data){
        Role role = new Role();

        role.setName(data.getName());

        List<Permission> permissions = new ArrayList<>();

        for(Integer permissionid : data.getPermissions()){
            Permission permission = permissionRepository.getReferenceById(permissionid);
            permissions.add(permission);
        }
        role.setPermissions(permissions);
        roleRepository.save(role);


        RoleDataDTO response = new RoleDataDTO();
        response.setId(role.getId());
        response.setName(role.getName());

        return response;
    }

    @GetMapping
    public List<RoleDataWithPermissionDTO> getAll(){

        List<Role> roles = roleRepository.findAll();
        List<RoleDataWithPermissionDTO> response = new ArrayList<>();

        for(Role role:roles){
            RoleDataWithPermissionDTO roleDataDTO = new RoleDataWithPermissionDTO();
            roleDataDTO.setId(role.getId());
            roleDataDTO.setName(role.getName());

            List<Permission> permissions = role.getPermissions();
            List<PermissionDTO> permissionDTOList = new ArrayList<>();

            for(Permission permission : permissions){
                PermissionDTO permissionDTO = new PermissionDTO();
                permissionDTO.setId(permission.getId());
                permissionDTO.setName(permission.getName());
                permissionDTO.setModule(permission.getModule());
                permissionDTOList.add(permissionDTO);
            }
            roleDataDTO.setPermissions(permissionDTOList);
            response.add(roleDataDTO);
        }

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){

        boolean exists  = roleRepository.existsById(id);

        if(!exists){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not Found");
        }

        roleRepository.deleteById(id);

    }

    @PutMapping("/{id}")
    public RoleDataDTO update(@PathVariable Integer id, @RequestBody RoleInsertDTO data){

        Optional<Role> optionalRole = roleRepository.findById(id);

        if(optionalRole.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not Found");
        }
        Role role = optionalRole.get();
        role.setName(data.getName());

        List<Permission> permissions = new ArrayList<>();

        for(Integer permissionId : data.getPermissions()) {
            Permission permission = permissionRepository.getReferenceById(permissionId);
            permissions.add(permission);
        }

        role.setPermissions(permissions);

        roleRepository.save(role);

        RoleDataDTO response = new RoleDataDTO();
        response.setId(role.getId());
        response.setName(role.getName());

        return response;

    }

    @GetMapping("/{id}")
    public RoleDetailDTO getDetails(@PathVariable Integer id){
        Optional<Role> optionalRoles = roleRepository.findById(id);

        if(optionalRoles.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not Found");
        }
        Role role = optionalRoles.get();
        RoleDetailDTO response = new RoleDetailDTO();

        response.setId(role.getId());
        response.setName(role.getName());

        List<Permission> permissions = role.getPermissions();
        List<PermissionDTO> permissionDTOList = new ArrayList<>();

        for(Permission permission : permissions){
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setId(permission.getId());
            permissionDTO.setName(permission.getName());
            permissionDTO.setModule(permission.getModule());
            permissionDTOList.add(permissionDTO);
        }
        response.setPermissions(permissionDTOList);

        response.setCreatedAt(role.getCreatedAt());
        response.setUpdatedAt(role.getUpdatedAt());

        User createdByUser = role.getCreatedBy();
        if(createdByUser != null){
            AuditUserDTO createdByAuditUser = new AuditUserDTO(createdByUser.getId(), createdByUser.getDisplayName());
            response.setCreatedBy(createdByAuditUser);
        }


        User updatedByUser = role.getUpdatedBy();
        if(updatedByUser != null){
            AuditUserDTO updatedByAuditUser = new AuditUserDTO(updatedByUser.getId(), updatedByUser.getDisplayName());
            response.setUpdatedBy(updatedByAuditUser);
        }

        return response;

    }
}
