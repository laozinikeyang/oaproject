package dao;

import java.util.Map;

import entity.SysLogin;

public interface SysLoginMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(SysLogin record);

    int insertSelective(SysLogin record);

    SysLogin selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(SysLogin record);

    int updateByPrimaryKey(SysLogin record);
    
    SysLogin selectByUsername(String username);
    
    Map<String,Long> selectByUsernameCount(String username);
}