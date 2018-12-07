package service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.SysLoginMapper;
import entity.SysLogin;
import servicedao.Sys_loginService;
@Service
public class Sys_loginServiceImpl implements Sys_loginService {

	@Resource
	SysLoginMapper loginDao;
	
	@Override
	public int deleteByPrimaryKey(Integer loginId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SysLogin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SysLogin record) {
		// TODO Auto-generated method stub
		return loginDao.insertSelective(record);
	}

	@Override
	public SysLogin selectByPrimaryKey(Integer loginId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysLogin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysLogin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysLogin selectByUsername(String username) {
		// TODO Auto-generated method stub
		return loginDao.selectByUsername(username);
	}

	@Override
	public Map<String, Long> selectByUsernameCount(String username) {
		// TODO Auto-generated method stub
		return loginDao.selectByUsernameCount(username);
	}

}
