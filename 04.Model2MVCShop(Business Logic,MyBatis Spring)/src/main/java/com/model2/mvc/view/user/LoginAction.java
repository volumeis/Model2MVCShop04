package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

/* ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
* ㅇ @ContextConfiguration : Meta-data location 지정
* ㅇ @Test : 테스트 실행 소스 지정
*/

//@ContextConfiguration(locations = { "classpath:/config/commonservice.xml" })
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
		
		
		//ApplicationContext context =
		//		new ClassPathXmlApplicationContext(	new String[] {	"/config/commonservice.xml"	 }
		//							                                   );
		
		//UserService userService = (UserService)context.getBean("userServiceImpl");
		
		//UserService userService=new UserServiceImpl();
		User dbUser= userService.getUser(user.getUserId());
		
		HttpSession session=request.getSession();
		session.setAttribute("user", dbUser);
		
		return "redirect:/index.jsp";
	}
}