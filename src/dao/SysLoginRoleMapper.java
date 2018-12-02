package dao;

import entity.SysLoginRole;

public interface SysLoginRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLoginRole record);

    int insertSelective(SysLoginRole record);

    SysLoginRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLoginRole record);

    int updateByPrimaryKey(SysLoginRole record);
}