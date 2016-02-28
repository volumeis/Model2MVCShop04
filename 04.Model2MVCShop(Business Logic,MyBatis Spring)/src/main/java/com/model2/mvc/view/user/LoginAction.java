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

/* �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
* �� @ContextConfiguration : Meta-data location ����
* �� @Test : �׽�Ʈ ���� �ҽ� ����
*/

//@ContextConfiguration(locations = { "classpath:/config/commonservice.xml" })
public class LoginAction extends Action{

	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
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