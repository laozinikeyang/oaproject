package dao;

import entity.BaseUserinfo;

public interface BaseUserinfoMapper {
    int deleteByPrimaryKey(String baseId);

    int insert(BaseUserinfo record);

    int insertSelective(BaseUserinfo record);

    BaseUserinfo selectByPrimaryKey(String baseId);

    int updateByPrimaryKeySelective(BaseUserinfo record);

    int updateByPrimaryKey(BaseUserinfo record);
}