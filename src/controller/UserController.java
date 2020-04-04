package controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Pojo.User;
import service.UserService;

@Controller

public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String userList() throws Exception{
		
		
		return "login";
		
	}
		@RequestMapping("/logincheck")
	public String login( User user, Model model, HttpSession httpSession) throws Exception{

			User user1 = userService.login(user);

		if(user1!=null){
			httpSession.setAttribute("user", user1);
			if(user1.getType().equals("zuke")){
				return "zuke/main";
			}
			else{
				return "admin/main1";
			}
		}else{
			String error="error";
			model.addAttribute("error", error);
		return "login";
		}
	}

	@RequestMapping("/register")
	public String register( User user, Model model, HttpSession httpSession) throws Exception{
		if(StringUtils.isEmpty(user.getType())||StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getUsername())){
			String error="参数错误";
			model.addAttribute("errorMsg", error);
			return "register";
		}
		/**
		 * 先判断账号是否存在
		 * */
		List<User> queryResult =userService.listUserByName(user);
		if(queryResult.size()>0){
			String error="当前账号已存在";
			model.addAttribute("errorMsg", error);
			return "register";
		}
		/**
		 * 开始注册
		 * */
		int result = userService.register(user);

		if(result==0){
			String error="注册失败";
			model.addAttribute("errorMsg", error);
			return "register";
		}
		return "login";
	}

	@RequestMapping("/toindex")
	public String toindex(Model model) throws Exception{
		return "admin/index";
	}

	@RequestMapping("/registerPage")
	public String registerPage(Model model) throws Exception{
		return "register";
	}
}



