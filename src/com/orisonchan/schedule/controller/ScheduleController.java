package com.orisonchan.schedule.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orisonchan.schedule.service.ClassiService;
import com.orisonchan.schedule.service.ScheduleService;
import com.orisonchan.schedule.service.UserService;
import com.orisonchan.schedule.util.Message;
import com.orisonchan.schedule.vo.ScheduleVO;

/**
 * 包含路径：<br>
 * /schedule/show.do 展示行程，有非必要传参classiId用于分类查询<br>
 * /schedule/detail.do 行程详情<br>
 * /schedule/new.do 展示新建行程界面<br>
 * /schedule/add.do 添加行程<br>
 * /schedule/{id}/detail.do 查看行程详情<br>
 * /schedule/{id}/update.do 修改行程<br>
 * /schedule/{id}/delete.do 删除行程<br>
 * 涉及session：<br>
 * userid 登录用户id<br>
 * 
 * 
 * @author Orison Chan
 *
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private UserService userService;

	@Autowired
	private ClassiService classiService;

	@Autowired
	protected ScheduleService scheduleService;

	/**
	 * 展示行程，有非必要传参classiId用于分类查询
	 * 
	 * @param classiId
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/show.do")
	public String show(@RequestParam(value = "classiId", required = false) Integer classiId, HttpSession httpSession,
			Model model, HttpServletRequest request) {
		Integer userid = (Integer) httpSession.getAttribute("userid");
		if (userid == null)
			return "redirect:/forcelogout.do";
		List list;
		if (classiId == null || classiId == 0){
			list = scheduleService.queryAllByuserId(userid);
			request.setAttribute("classiId", 0);
		}			
		else{
			list = scheduleService.findByUserIdNClassId(userid, classiId);
			request.setAttribute("classiId", classiId);
		}
			
		model.addAttribute("user", userService.getUserInfo(userid));
		model.addAttribute("schedulelist", list);
		return "schedule/view";
	}

	/**
	 * 新建行程界面
	 * 
	 * @return
	 */
	@RequestMapping("/new.do")
	public String newadd(Model model,HttpSession httpSession, HttpServletRequest request) {
		Integer userid = (Integer) httpSession.getAttribute("userid");
		if (userid == null)
			return "redirect:/forcelogout.do";
		model.addAttribute("clazzlist", classiService.queryAllByuserId(userid));
		return "schedule/add";
	}

	/**
	 * 添加行程
	 * 
	 * @param start_time
	 * @param end_time
	 * @param title
	 * @param content
	 * @param classiId
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public Message add(@RequestParam("start_time") String start_time, @RequestParam("end_time") String end_time,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("classiId") Integer classiId, HttpSession httpSession) {
		Message m = new Message();
		Integer userid = (Integer) httpSession.getAttribute("userid");
		try {
			scheduleService.addschedule(Timestamp.valueOf(start_time), Timestamp.valueOf(end_time), title, content, userid, classiId);
			m.setMessage(Message.MESSAGE_SUCCESS);
		} catch (Exception e) {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}

	/**
	 * 行程详情查看
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}/detail.do")
	public String detail(@PathVariable("id") Integer id, Model model, HttpServletRequest request, HttpSession httpSession) {
		Integer userid = (Integer) httpSession.getAttribute("userid");
		if (userid == null)
			return "redirect:/forcelogout.do";
		model.addAttribute("clazzlist", classiService.queryAllByuserId(userid));
		model.addAttribute("user", userService.getUserInfo(id));
		model.addAttribute("scheVO", scheduleService.findById(id));
		return "schedule/detail";
	}

	/**
	 * 更新行程
	 * 
	 * @param id
	 * @param start_time
	 * @param end_time
	 * @param title
	 * @param content
	 * @param classiId
	 * @return
	 */
	@RequestMapping("/{id}/update.do")
	@ResponseBody
	public Message update(@PathVariable("id") Integer id, @RequestParam("start_time") String start_time,
			@RequestParam("end_time") String end_time, @RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("classiId") Integer classiId) {
		Message m = new Message();
		ScheduleVO svo = scheduleService.findById(id);
		svo.setStart_time(Timestamp.valueOf(start_time));
		svo.setEnd_time(Timestamp.valueOf(end_time));
		svo.setTitle(title);
		svo.setContent(content);
		svo.setClassiId(classiId);
		try {
			scheduleService.updateschedule(svo.retranform());
			m.setMessage(Message.MESSAGE_SUCCESS);
		} catch (Exception e) {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}

	@RequestMapping("/{id}/delete.do")
	@ResponseBody
	public Message deleteById(@PathVariable("id") Integer id) {
		Message m = new Message();
		try {
			scheduleService.delete(id);
			m.setMessage(Message.MESSAGE_SUCCESS);
		} catch (Exception e) {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}

}
