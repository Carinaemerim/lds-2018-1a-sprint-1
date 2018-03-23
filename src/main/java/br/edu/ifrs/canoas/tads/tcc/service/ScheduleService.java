package br.edu.ifrs.canoas.tads.tcc.service;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;
import br.edu.ifrs.canoas.tads.tcc.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	public final ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {
		super();
		this.scheduleRepository = scheduleRepository;
	}

	public Iterable<Task> listAll() {
		return scheduleRepository.findAll();
	}
	
}
