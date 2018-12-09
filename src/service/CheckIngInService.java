package service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import entity.CheckingIn;



@Service
public class CheckIngInService {
private int error1000 = 1000; //ÿ�����ֻ�ܴ򿨰˴�,�������ز���������1000
	
	@Resource
	JdbcTemplate jdbc;
	
	
	public List<Map<String,Object>> findMonthCkin (String username
			,String start,String end){
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		String sql = "SELECT ckin_id,ckin_content,ckin_times,ckin_date,username FROM "
				+ "checking_in WHERE username = ? AND ckin_date >= ? AND ckin_date <= ?";
		
		List<Map<String, Object>> tempList = jdbc.queryForList(sql,username,start,end);
		
		for (Map<String, Object> temParm : tempList){
			String ckinTimes = (String)temParm.get("ckin_times");
			String[] ckins = ckinTimes.split(",");
			for (String ckin : ckins){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("title", ckin);
				map.put("start", temParm.get("ckin_date"));
				if (temParm.get("ckin_content")!=null){
					map.put("content", temParm.get("ckin_content"));
				}else{
					map.put("content", "");
				}
				resultList.add(map);
			}
		}
		
		return resultList;
	}
	
public int ckin (CheckingIn ckin){
		
		String sql = "SELECT ckin_times FROM checking_in WHERE username = ? "
				+ "AND ckin_date = ?";
		Map<String,Object> ckinMap = FindTodayCkin(ckin);
		Object cktimeObj = null;
		if (ckinMap != null){
			cktimeObj = ckinMap.get("ckin_times");
		}
		if (cktimeObj!=null){
			//���´򿨼�¼
			String ckString = (String)cktimeObj;
			if (ckString.split(",").length<8){
				ckin.setCkin_times(ckString+","+ckin.getCkin_times());
				String updateSql = "UPDATE checking_in AS ckin SET ckin.ckin_content=?, "
						+ "ckin.ckin_times=? WHERE ckin_date = ? AND username = ?";
				return jdbc.update(updateSql, ckin.getCkin_content(),ckin.getCkin_times()
						,ckin.getCkin_date(),ckin.getUsername());
			}else{
				//�򿨳�������
				return error1000;
			}
			
		}else{
			//�½��򿨼�¼ ���δ�
			String insertSql = "INSERT INTO checking_in (ckin_id,ckin_content,ckin_times"
					+ ",ckin_date,username) VALUES (?,?,?,?,?)";
			return jdbc.update(insertSql,ckin.getCkin_id(),ckin.getCkin_content()
					,ckin.getCkin_times(),ckin.getCkin_date(),ckin.getUsername());
		}
		//jdbc.queryForList(sql, args, elementType)
	}
	
	
	
	//���������Ҫ�ǻ�ȡ����Ĵ���Ϣ,�����Ը������ڲ�ѯ����
		public Map<String,Object> FindTodayCkin (CheckingIn ckin){
			
			String sql = "SELECT ckin_id,ckin_content,ckin_times,ckin_date,username FROM "
					+ "checking_in WHERE ckin_date = ? AND username = ? ";
			List<Map<String,Object>> ckinList = jdbc.queryForList(sql,ckin.getCkin_date()
					,ckin.getUsername());
			if (ckinList !=null && ckinList.size() == 1){
				return ckinList.get(0);
			}else{
				return null;
			}
		}
}
