package service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SystemPluService {
	
	@Resource
	JdbcTemplate jdbc;
	
	public List<Map<String,Object>> getDepartment (){
		String sql = "SELECT DISTINCT role_groupname,role_group "
				+ "FROM sys_role WHERE role_group <> '-1'";
		
		return jdbc.queryForList(sql);
	}
	
	
	
	public Map<String,Object> getWxnameByUsername (String username){
		System.out.println("username:"+username);
		String sql = "select wxname from sys_login where username = ?";
		Map<String, Object> result = jdbc.queryForMap(sql,username);
		
		return result;
	}

}
