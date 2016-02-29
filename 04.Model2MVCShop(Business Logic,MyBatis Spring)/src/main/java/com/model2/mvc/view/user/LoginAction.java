package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


/* 수정함*/
public class LoginAction extends Action{

	// ==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		User user=new User();
		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(	new String[] {	"/config/commonservice.xml"	 } );
		
		UserService userService = (UserService)context.getBean("userServiceImpl");
		User dbUser= userService.getUser(user.getUserId());
		
		HttpSession session=request.getSession();
		session.setAttribute("user", dbUser);
		
		return "redirect:/index.jsp";
	}
}