package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {
	
	@RequestMapping(value = "list" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personVo = phoneDao.getPersonList();
		
		System.out.println(personVo.toString());
		
		model.addAttribute("pList",personVo);
		
		
		return "/WEB-INF/views/list.jsp";
	}
	
	@RequestMapping(value = "writeForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String wtireForm() {
		System.out.println("writeForm");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	@RequestMapping(value = "write",method = {RequestMethod.GET,RequestMethod.POST})
	public String wtire(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		
		
		System.out.println("write");
		System.out.println(name+","+hp+","+company);
		
		PersonVo personVo = new PersonVo(name,hp,company);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value = "modifyForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam("personId") String personId,Model model) {
		
		System.out.println("modify");
		System.out.println(personId);
		int pId = Integer.parseInt(personId);
		
		
		PersonVo personVo = new PersonVo();
		PhoneDao phoneDao = new PhoneDao();
		personVo = phoneDao.getPerson(pId);
		System.out.println(personVo.toString());
		model.addAttribute("pList",personVo);
		return "/WEB-INF/views/modifyForm.jsp";
	}
	
	@RequestMapping(value = "modify",method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@RequestParam("personId") String personId,
						 @RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company) {
		
		
		int pId = Integer.parseInt(personId);
		
		PersonVo personVo = new PersonVo(pId,name,hp,company);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value = "delete",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("personId") String personId) {
		
		System.out.println("modify");
		System.out.println(personId);
		int pId = Integer.parseInt(personId);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.parsonDelete(pId);
		
		
		return "redirect:/phone/list";
	}
	
	
}
