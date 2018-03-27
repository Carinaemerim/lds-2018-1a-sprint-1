package br.edu.ifrs.canoas.tads.tcc.service;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;
import br.edu.ifrs.canoas.tads.tcc.repository.TaskRepository;

@Service
public class ScheduleService {
	
	public final TaskRepository scheduleRepository;

	public ScheduleService(TaskRepository scheduleRepository) {
		super();
		this.scheduleRepository = scheduleRepository;
	}

	public String getPeriod() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		String period = "" + ano;
		if(Calendar.getInstance().get(Calendar.MONTH) < 7){
			period = period + "/01";
		}
		else {
			period = period + "/02";
		}
		return period;
	}
	
	public Iterable<Task> listAll() {
		return scheduleRepository.findByPeriod(getPeriod());
	}
	
	public boolean delete(Long id){
        try {
            scheduleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public Task save(Task task) {
		if(task.getId() == null)
			scheduleRepository.save(task);
		return scheduleRepository.save(task);
	}

	public Task getId(Long id) {
		return scheduleRepository.getOne(id);
	}

	
}
