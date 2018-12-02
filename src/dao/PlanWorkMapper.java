package dao;

import entity.PlanWork;

public interface PlanWorkMapper {
    int deleteByPrimaryKey(String planId);

    int insert(PlanWork record);

    int insertSelective(PlanWork record);

    PlanWork selectByPrimaryKey(String planId);

    int updateByPrimaryKeySelective(PlanWork record);

    int updateByPrimaryKeyWithBLOBs(PlanWork record);

    int updateByPrimaryKey(PlanWork record);
}