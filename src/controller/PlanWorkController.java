package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.PlanWork;
import service.PlanWorkService;
import service.SystemPluService;

@Controller
public class PlanWorkController {
	@Resource
	SimpleDateFormat sdf;
	@Resource
	PlanWorkService ps;
	
	@Resource
	SystemPluService syspluService;
	
	@RequestMapping(value="/updatePlanWork")
	public ModelAndView updatePlanWork (HttpServletRequest request,PlanWork planWork){

		
		ps.updateHC(planWork);
		
		return goToday(request,null);
	}
	
	
	
	@RequestMapping(value="/planByid")
	@ResponseBody
	public Map<String,Object> planByid (String plan_id){
		return ps.selectById(plan_id);
	}
	
	
	@RequestMapping(value="/planGoAll")
	@ResponseBody
	public Map<String,Object> goAll (HttpServletRequest request,Integer page){
		Map<String,Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("UserName");
		Integer tempPage = page;
		if (tempPage >0){
			tempPage = (tempPage-1)*7;
		}
		List<Map<String, Object>> pw7 = ps.selectByPlanTo(tempPage,7,username,"1");
		if (pw7!=null &&pw7.size()>0){
			map.put("success", true);
			map.put("data", pw7);
			map.put("page", page);
		}else{
			map.put("success", false);
		}
		
		return map;
	}
	
	
	
	
	@RequestMapping(value="/planGoToday")
	@ResponseBody
	public ModelAndView goToday (HttpServletRequest request,Integer page){
		ModelAndView mav = new ModelAndView("planwork");
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("UserName");
		//��ȡ���7���ϱ�����
		mav.addObject("top7",ps.selectByPlanTo(0,7,username,"1"));
		//����
		long count = ps.selectPlanToCount(username);
		mav.addObject("count",count);
		//����
		mav.addObject("row",7);
		
		if(count%7>0){
			mav.addObject("lastPage",count/7+1);
		}else{
			mav.addObject("lastPage",count/7);
		}
		
		//��������ɵ�
		mav.addObject("top7Over",ps.selectByPlanToFlag23(0,7,username,"1"));
		//����
		long countOver = ps.selectPlanToCount23(username);
		mav.addObject("countOver",countOver);
		
		if(count%7>0){
			mav.addObject("lastPageOver",countOver/7+1);
		}else{
			mav.addObject("lastPageOver",countOver/7);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value="/getContent")
	@ResponseBody
	public Map<String,Object> getContent(HttpServletRequest request,Integer page){
		Map<String,Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("UserName");
		Integer tempPage = page;
		if (tempPage >0){
			tempPage = (tempPage-1)*7;
		}
		List<Map<String, Object>> pw7 = ps.selectPlanWork(tempPage,7,username);
		if (pw7!=null &&pw7.size()>0){
			map.put("success", true);
			map.put("data", pw7);
			map.put("page", page);
		}else{
			map.put("success", false);
		}
		
		return map;
	}
	
	
	@RequestMapping("/savePlanWork")
	public ModelAndView savePlanWork (HttpServletRequest request,PlanWork planWork){
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("UserName");
		
		//���ʵ���࣬����ID
		planWork.setPlan_id(UUID.randomUUID().toString());
		//������
		planWork.setPlan_creat(username);
		//ʱ���ʽת��
		sdf.applyPattern("yyyy-MM-dd hh:mm:ss");
		//���ô���ʱ��
		planWork.setPlan_creatime(sdf.format(new Date()));
		//�����û�����ȡ��ʵ����
		Map<String, Object> result=syspluService
				                            .getWxnameByUsername(planWork.getPlan_to());
		//����������
		planWork.setPlan_update((String)result.get("wxname"));
		//����ʱ��
		planWork.setPlan_updatetime(sdf.format(new Date()));
		//���,�걨���Ϊ1�걨״̬,2ͬ��,3��ͬ��
		planWork.setPlan_flag("1");
		//�������ɹ�����1������ˢ��ǰ̨ҳ�棬������1���򷵻ر���ҳ��
		if (ps.savePlanWork(planWork)==1){
			mav.setViewName("redirect:/mainContent");
		}else {
			mav.setViewName("redirect:/500.jsp");
		}
		
		return mav;
		
	}
	

}
