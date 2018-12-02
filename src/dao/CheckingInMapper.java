package dao;

import entity.CheckingIn;

public interface CheckingInMapper {
    int deleteByPrimaryKey(String ckinId);

    int insert(CheckingIn record);

    int insertSelective(CheckingIn record);

    CheckingIn selectByPrimaryKey(String ckinId);

    int updateByPrimaryKeySelective(CheckingIn record);

    int updateByPrimaryKey(CheckingIn record);
}