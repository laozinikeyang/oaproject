package entity;

public class SysRole {
    private Integer roleId;

    private String roleName;

    private String roleDes;

    private Integer rolePid;

    private String roleGroup;

    private String roleGroupname;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDes() {
        return roleDes;
    }

    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }

    public Integer getRolePid() {
        return rolePid;
    }

    public void setRolePid(Integer rolePid) {
        this.rolePid = rolePid;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public String getRoleGroupname() {
        return roleGroupname;
    }

    public void setRoleGroupname(String roleGroupname) {
        this.roleGroupname = roleGroupname;
    }
}