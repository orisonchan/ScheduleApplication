package com.orisonchan.schedule.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orisonchan.schedule.bean.Classi;
import com.orisonchan.schedule.service.ClassiService;
import com.orisonchan.schedule.service.UserService;
import com.orisonchan.schedule.util.Message;

/**
 * 包含路径：<br>
 * /clazz/add.do 添加类别，返回json格式message，更新session<br>
 * /clazz/show.do 展示类别，返回model<br>
 * 涉及session：<br>
 * userid 登录用户id<br>
 * 
 * @author Orison Chan
 *
 */
@Controller
@RequestMapping("/clazz")
public class ClassiController {

	@Autowired
	private UserService userService;

	@Autowired
	protected ClassiService classiService;

	/**
	 * 添加类别，返回json格式message
	 * 
	 * @param name
	 * @param parentId
	 * @param httpSession
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public Message add(@RequestParam("name") String name, @RequestParam("parentId") int parentId,
			HttpSession httpSession) {
		Message m = new Message();
		int userid = (Integer) httpSession.getAttribute("userid");
		List classlist = classiService.queryAllByuserId(userid);
		Iterator it = classlist.iterator();
		int level = 0;
		while (it.hasNext()) {
			Classi classi = (Classi) it.next();
			if (classi.getId() == parentId) {
				level = classi.getLevel() + 1;
				break;
			}
		}
		Integer classiId = classiService.add(name, parentId, level, userid);
		if (classiId != null) {
			m.setMessage(Message.MESSAGE_SUCCESS);
		} else
			m.setMessage(Message.MESSAGE_ERROR);
		return m;
	}

	@RequestMapping("/show.do")
	public String show(HttpSession httpSession, Model model, HttpServletRequest request) {
		Integer userid = (Integer) httpSession.getAttribute("userid");
		if (userid == null)
			return "redirect:/forcelogout.do";
		model.addAttribute("clazzlist", classiService.queryAllByuserId(userid));
		model.addAttribute("user", userService.getUserInfo(userid));
		return "classi/manage";
	}
	
	@RequestMapping("/{id}/delete.do")
	@ResponseBody
	public Message deleteById(@PathVariable("id") Integer id) {
		Message m = new Message();
		try {
			classiService.delete(id);
			m.setMessage(Message.MESSAGE_SUCCESS);
		} catch (Exception e) {
			m.setMessage(Message.MESSAGE_ERROR);
		}
		return m;
	}
}
