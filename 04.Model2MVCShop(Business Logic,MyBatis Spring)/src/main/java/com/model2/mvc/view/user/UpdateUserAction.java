package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

//¼öÁ¤ÇÔ
public class UpdateUserAction extends Action {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(	new String[] {	"/config/commonservice.xml"	 } );
		
		UserService userService = (UserService)context.getBean("userServiceImpl");
		
		String userId=(String)request.getParameter("userId");
		
		User user=new User();
		user.setUserId(userId);
		user.setUserName(request.getParameter("userName"));
		user.setAddr(request.getParameter("addr"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		
		userService.updateUser(user);
		
		HttpSession session=request.getSession();
		String sessionId=((User)session.getAttribute("user")).getUserId();
		
		if(sessionId.equals(userId)){
			session.setAttribute("user", user);
		}
		
		return "redirect:/getUser.do?userId="+userId;
	}
}