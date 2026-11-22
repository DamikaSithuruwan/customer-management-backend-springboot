package lk.brightenacademy.customer_demo.dto;

import java.util.List;

public class RoleDataWithPermissionDTO extends RoleDataDTO {

    private List<PermissionDTO> permissions;
    private long userCount;

    public long getUserCount() { return userCount; }
    public void setUserCount(long userCount) { this.userCount = userCount; }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }


}
