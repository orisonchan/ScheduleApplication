package com.orisonchan.schedule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orisonchan.schedule.bean.User;
import com.orisonchan.schedule.service.ClassiService;
import com.orisonchan.schedule.service.ScheduleService;
import com.orisonchan.schedule.service.UserService;
import com.orisonchan.schedule.util.Message;

/**
 * 包含路径：<br>
 * /verifycode.do 验证验证码，返回json格式message<br>
 * /login.do 登陆界面(show login)<br>
 * /signin.do 验证登录<br>
 * /logout.do 退出登录(logout)<br>
 * /index.do 主界面(show index)<br>
 * /signup.do 注册，返回json格式message<br>
 * /update.do 修改个人信息，返回json格式message<br>
 * /showinfo.do 展示个人信息<br>
 * 涉及session：<br>
 * com.servlet.codenumber 验证码<br>
 * userid 登录用户id<br>
 * 
 * 
 * 
 * @author Orison Chan
 *
 */
@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private ClassiService classiService;

	/**
	 * 验证验证码 verify verification code
	 * 
	 * @param code
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/verifycode.do", method = RequestMethod.POST)
	@ResponseBody
	public Message verifycode(@RequestParam("code") String code, HttpSession httpSession) {
		String sessioncode = (String) httpSession.getAttribute("com.servlet.codenumber");
		Message m = new Message();
		if (sessioncode.equalsIgnoreCase(code)) {
			m.setMessage(Message.MESSAGE_SUCCESS);
		} else {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}

	/**
	 * 登陆界面(show login)<br>
	 * 检查session，有跳转至index，无跳转至login
	 */
	@RequestMapping("/login.do")
	public String showLogin(HttpSession httpSession) {
		Integer userid = (Integer) httpSession.getAttribute("userid");
		if (userid != null)
			return "redirect:index.do";
		return "user/login";
	}

	/**
	 * 退出登录(logout)<br>
	 * 清除缓存
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "user/login";
	}

	/**
	 * 验证登陆
	 * 
	 * @param username
	 * @param password
	 * @param hs
	 * @return
	 */
	@RequestMapping(value = "/signin.do", method = RequestMethod.POST)
	@ResponseBody
	public Message signin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession hs) {
		Message m = new Message();
		Integer userid = userService.validLogin(username, password);
		if (userid != null) {
			hs.setAttribute("userid", userid);
			m.setMessage(Message.MESSAGE_SUCCESS);
		} else
			m.setMessage(Message.MESSAGE_ERROR);
		return m;
	}

	/**
	 * 主界面(show index)<br>
	 * 检查session，有跳转至index，无跳转至forcelogout强迫退出
	 */
	@RequestMapping("/index.do")
	public String showIndex(Model model, HttpSession httpSession, HttpServletRequest request) {
		Integer userId = (Integer) httpSession.getAttribute("userid");
		if (userId == null)
			return "redirect:login.do";
		model.addAttribute("user", userService.getUserInfo(userId));
		model.addAttribute("total_schedules_count", scheduleService.CountByUserId(userId));
		//request.setAttribute("today_schedules_list", scheduleService.TodaySchedule(userId));
		model.addAttribute("today_schedules_count", scheduleService.TodayScheduleCount(userId));
		model.addAttribute("total_classes_count", classiService.CountByUserId(userId));
		model.addAttribute("today_schedules_list", scheduleService.TodaySchedule(userId));
		model.addAttribute("within_oneweek", classiService.queryCreateWithinOneWeekClass(userId));
		request.setAttribute("total_classes", classiService.queryAllByuserId(userId));
		return "user/index";
	}

	/**
	 * 注册(sign up)<br>
	 * 返回json格式message。如注册成功，则添加session，前台收到成功消息会自动跳转登录
	 */
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	@ResponseBody
	public Message signup(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("gender") String gender, @RequestParam("age") int age, HttpSession httpSession) {
		Message m = new Message();
		try {
			if (username.equals(""))
				m.setMessage("nullname");
			else {
				Integer userId = userService.signUp(username, password, gender, age);
				httpSession.setAttribute("userid", userId);
				m.setMessage(Message.MESSAGE_SUCCESS);
			}
		} catch (Exception e) {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}

	/**
	 * 修改个人信息<br>
	 * 返回json格式message。
	 */
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	@ResponseBody
	public Message update(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("gender") String gender, @RequestParam("age") int age, HttpSession httpSession) {
		Message m = new Message();
		Integer userId = (Integer) httpSession.getAttribute("userid");
		User user = userService.getUserInfo(userId);
		user.setUsername(username);
		user.setPassword(password);
		user.setGender(gender);
		user.setAge(age);
		try {
			userService.update(user);
			m.setMessage(Message.MESSAGE_SUCCESS);
		} catch (Exception e) {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}

	/**
	 * 展示个人信息<br>
	 */
	@RequestMapping("/showinfo.do")
	public String showinfo(Model model, HttpSession httpSession, HttpServletRequest request) {
		Integer userId = (Integer) httpSession.getAttribute("userid");
		model.addAttribute("user", userService.getUserInfo(userId));
		request.setAttribute("user", userService.getUserInfo(userId));
		return "user/user-info";
	}

}
