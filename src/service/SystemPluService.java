package service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SystemPluService {
	
	@Resource
	JdbcTemplate jdbc;
	public Map<String,Object> getWxnameByUsername (String username){
		System.out.println("username:"+username);
		String sql = "select wxname from sys_login where username = ?";
		Map<String, Object> result = jdbc.queryForMap(sql,username);
		
		return result;
	}

}
