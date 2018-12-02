package entity;

public class SysPermission {
    private Integer permissionId;

    private String permissionName;

    private String permissionMenuName;

    private Integer permissionMenuPid;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionMenuName() {
        return permissionMenuName;
    }

    public void setPermissionMenuName(String permissionMenuName) {
        this.permissionMenuName = permissionMenuName;
    }

    public Integer getPermissionMenuPid() {
        return permissionMenuPid;
    }

    public void setPermissionMenuPid(Integer permissionMenuPid) {
        this.permissionMenuPid = permissionMenuPid;
    }
}