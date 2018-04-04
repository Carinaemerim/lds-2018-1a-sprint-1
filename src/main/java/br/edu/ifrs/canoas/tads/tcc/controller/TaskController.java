package br.edu.ifrs.canoas.tads.tcc.controller;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;
import br.edu.ifrs.canoas.tads.tcc.service.ScheduleService;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/schedule")

@Controller
public class TaskController {
	
	private final ScheduleService scheduleService;

	public TaskController(ScheduleService scheduleService) {
		super();
		this.scheduleService = scheduleService;
	}

	@GetMapping("/index")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("/schedule/index");
		mav.addObject("currPeriod", scheduleService.getPeriod());
		mav.addObject("tasks", scheduleService.listAll());
		return mav;
	}
	@Secured("ROLE_PROFESSOR")
	@GetMapping("/add")
	public String newTask(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("currPeriod", scheduleService.getPeriod());
		return "/schedule/edit";
	}
	@Secured("ROLE_PROFESSOR")
	@PostMapping("/edit")
	public String save(Task task) {
		task.setPeriod(scheduleService.getPeriod());		
		scheduleService.save(task);
		return "redirect:/schedule/index";
	}
	@Secured("ROLE_PROFESSOR")
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {  
		ModelAndView mav = new ModelAndView("/schedule/edit");
		mav.addObject("currPeriod", scheduleService.getPeriod());
		mav.addObject("task", scheduleService.getId(id));	
		return mav;
    }
	@Secured("ROLE_PROFESSOR")
	@GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("redirect:/schedule/index");
        scheduleService.delete(id);
        return mav;
    }
	
	@GetMapping("/next")
	public ModelAndView next() {
		ModelAndView mav = new ModelAndView("/schedule/index");
		mav.addObject("currPeriod", scheduleService.next());
		mav.addObject("tasks", scheduleService.listAll());
		return mav;
	}
	
	@GetMapping("/previous")
	public ModelAndView previous() {
		ModelAndView mav = new ModelAndView("/schedule/index");
		mav.addObject("currPeriod", scheduleService.previous());
		mav.addObject("tasks", scheduleService.listAll());
		return mav;
	}


}
