package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import entity.SysLogin;
import servicedao.Sys_loginService;
import util.MD5;
import util.ValidataUtil;

@Controller
@RequestMapping("/login")
public class DengluController {
	
	@Autowired
	Sys_loginService userloginService;
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView("redirect:/index.jsp");
		
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return mav;
	}
	
	
	 @RequestMapping(value="/register.do")
	    public ModelAndView register(HttpServletRequest request,String username , String password ,String repassword , String email , String wxname) {
	    	ModelAndView mav = new ModelAndView("redirect:/index.jsp");
	    	
	    	if (username!=null&&!"".equals(username.trim())&&username.length()<20&&password!=null&&!"".equals(password.trim())&&(password.equals(repassword))){
	    		
	    		Long count = userloginService.selectByUsernameCount(username).get("count");
	    		if (0==count){
	    			SysLogin entity = new SysLogin();
	    			entity.setUsername(username);
	    			entity.setPassword(MD5.md5(password));
	    			if (email!=null&&!"".equals(email.trim())&&ValidataUtil.isEmail(email)){
	    				entity.setEmail(email);
	    			}
	    			
	    			if (wxname!=null&&!"".equals(wxname.trim())){
	    				entity.setWxname(wxname);
	    			}
	    			
	    			if(userloginService.insertSelective(entity)>0){
	    				mav.addObject("msg", "注册成功");
	    			}
	    		}else{
	    			mav.addObject("msg", "注册失败");
	    		}
	    	}
	    	
	    	return mav;    	
	    }
	
	
	
	@RequestMapping(value="/verification.do")
	public ModelAndView login(HttpServletRequest request,String username , String password ,@RequestParam(defaultValue="0000") String verifyCode, Model model) {
		ModelAndView mav = new ModelAndView();
		String msg = "";
		HttpSession session = request.getSession();
		String SessionverifyCode = "0000";//(String)session.getAttribute("verifyCode");
		if (SessionverifyCode!=null&&SessionverifyCode.equals(verifyCode)){
			session.setAttribute("verifyCode", MD5.md5(Math.random()+""));
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			//token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			try{
				subject.login(token);
				SysLogin loginEntity = userloginService.selectByUsername(username);
				session.setAttribute("UserName", username);
				session.setAttribute("loginEntity", loginEntity);
				mav.setViewName("redirect:/index");
				System.out.println("登陆成功！");
			}catch(IncorrectCredentialsException e) {
		        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    } catch (ExcessiveAttemptsException e) {  
		        msg = "登录失败次数过多";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    } catch (LockedAccountException e) {  
		        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    } catch (DisabledAccountException e) {
		        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    } catch (ExpiredCredentialsException e) {
		        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    } catch (UnknownAccountException e) {
		        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    } catch (UnauthorizedException e) {
		        msg = "您没有得到相应的授权！" + e.getMessage();  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		        mav.setViewName("redirect:/index.jsp");
		    }
		}else{
			mav.addObject("msg", "验证码错误！");
			mav.setViewName("redirect:/index.jsp");
		}
		return mav;
	}
}
