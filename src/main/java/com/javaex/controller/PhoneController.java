package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		
		return "list";
	}
	
	@RequestMapping(value = "writeForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String wtireForm() {
		System.out.println("writeForm");
		
		return "writeForm";
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
	
	@RequestMapping(value = "/modifyForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam("personId") int pId,Model model) {
			
		PersonVo personVo = new PersonVo();
		PhoneDao phoneDao = new PhoneDao();
		personVo = phoneDao.getPerson(pId);
		System.out.println(personVo.toString());
		model.addAttribute("pList",personVo);
		return "modifyForm";
	}
	
	@RequestMapping(value = "/modify2",method = {RequestMethod.GET,RequestMethod.POST})
	public String modify2(@RequestParam("personId") int pId,
						  @RequestParam("name") String name,
						  @RequestParam("hp") String hp,
						  @RequestParam("company") String company) {
	
		
		PersonVo personVo = new PersonVo(pId,name,hp,company);
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value = "/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo personVo) {
	
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		return "redirect:/phone/list";
		
	}
	
	@RequestMapping(value = "/delete/{personId}",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@PathVariable("personId") int pId) {
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.parsonDelete(pId);
		
		
		return "redirect:/phone/list";
	}
	@RequestMapping(value = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete2(@RequestParam("personId") int pId) {
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.parsonDelete(pId);
		
		
		return "redirect:/phone/list";
	}
	
	
	
}
