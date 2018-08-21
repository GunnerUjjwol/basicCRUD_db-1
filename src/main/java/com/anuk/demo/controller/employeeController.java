package com.anuk.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anuk.demo.dao.employeeDao;
import com.anuk.demo.model.Employee;


@Controller
public class employeeController {

	@Autowired
	employeeDao empService;


	
	@GetMapping("/")
	public String welcome()
	{
		return "welcome";
	}

	@GetMapping("show")
	public String showForm() {

		return "empForm";
	}

	@PostMapping("save")
	public ModelAndView save(@Valid @ModelAttribute("emp") Employee emp, BindingResult br) {
		if(br.hasErrors())
		{
			return new ModelAndView("empForm", "emp", new Employee());
		}
		
		empService.save(emp);
		return new ModelAndView("redirect:/empView");
	}

	@GetMapping("empView")
	public String viewEmp(Model m) {
List<Employee> empList = new ArrayList<>();
	
	empService.findAll()
	.forEach(empList::add);	
    m.addAttribute("empList", empList);
  return "empView";

	}

	@RequestMapping("empUpdate")
	public String empUpdate(@RequestParam("id") int id, Model m) {
		
		m.addAttribute("emp", empService.findById(id));

		return "empUpdate";

	}

	@RequestMapping("saveUpdate")
	public String saveUpdate(@ModelAttribute("emp") Employee emp) {

		empService.save(emp);
		return "redirect:/empView";

	}

	@RequestMapping("empDelete")
	public String empDelete(@RequestParam("id") int id) {
		empService.deleteById(id);
		return "redirect:/empView";
	}

	@ResponseBody
	@RequestMapping("empJson")
	public List<Employee> empJson()
	{List<Employee> empList = new ArrayList<>();

	empService.findAll()
	.forEach(empList::add);	
	return empList;
	}
	
	
}
