package br.edu.ifrs.canoas.tads.tcc.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;
import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;

@RequestMapping("/schedule")
@Controller
public class ScheduleController {
	
	private final ScheduleService scheduleService;

	public ScheduleController(ScheduleService scheduleService) {
		super();
		this.scheduleService = scheduleService;
	}

	@GetMapping("/index")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("/schedule/index");
		mav.addObject("tasks", scheduleService.listAll());
		mav.addObject("curr_period", scheduleService.getPeriod());
		return mav;
	}
	
	/*@GetMapping("/add")
	public String newTask() {
		ModelAndView mav = new ModelAndView("/schedule/edit");
		mav.addObject("task", scheduleService.save(new Task()));
		return "redirect:/schedule/index";
	}*/

	
	@GetMapping("/add")
	public String newTask(Model model) {
		model.addAttribute("task", new Task());
		return "/schedule/edit";
	}
	
	@PostMapping("/edit")
	public String save(@Valid Task task) {
		task.setPeriod(scheduleService.getPeriod());
		task.setTitle("teste");
		task.setDescription("fdddv fdg dfgfdgdgfgfdg");
		//task.setDeadline(new Date());
		scheduleService.save(task);
		return "redirect:/schedule/index";
	}
	
	//@GetMapping("/edit")
	//public ModelAndView edit() {
	//	return new ModelAndView("/schedule/edit");
	//}
	
	@GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("redirect:/schedule/index");
        scheduleService.delete(id);
        return mav;
    }

}
