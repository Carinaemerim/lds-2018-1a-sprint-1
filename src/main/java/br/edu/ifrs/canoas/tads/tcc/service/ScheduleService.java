package br.edu.ifrs.canoas.tads.tcc.service;

import java.time.LocalDate;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;
import br.edu.ifrs.canoas.tads.tcc.domain.TaskStatus;
import br.edu.ifrs.canoas.tads.tcc.repository.TaskRepository;

@Service
public class ScheduleService {
	
	private String period;
	public final TaskRepository scheduleRepository;

	public ScheduleService(TaskRepository scheduleRepository) {
		super();
		this.scheduleRepository = scheduleRepository;
		period = null;
	}

	public String getPeriod() {
		if(this.period == null) {
			int ano = Calendar.getInstance().get(Calendar.YEAR);
			String period = String.valueOf(ano);
			if(Calendar.getInstance().get(Calendar.MONTH) < 7){
				period = period + "/01";
			}
			else {
				period = period + "/02";
			}
			this.period = period;
		}
		return this.period;
	}
	
	public Iterable<Task> listAll() {
		return scheduleRepository.findByPeriod(period);
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

	
	public TaskStatus getTaskStatus(Long id) {
		TaskStatus taskStatus = null;
		
		if(this.getId(id).getDeadline().compareTo(LocalDate.now()) <= 0){
			return taskStatus.IN_PROGRESS;
		}
		
		return taskStatus.EXPIRED;
	}

	public String next() {
		if(period == null) {
			return "";
		}
		String[] values = period.split("/");
		int year = Integer.parseInt(values[0]);
		int semester = Integer.parseInt(values[1]);
		if(semester == 2) {
			year++;
			semester--;
		} else {
			semester++;
		}
		period = year+ "/0" + semester;	
		return period;
	}

	public String previous() {
		if(period == null) {
			return "";
		}
		String[] values = period.split("/");
		int year = Integer.parseInt(values[0]);
		int semester = Integer.parseInt(values[1]);
		if(semester == 1) {
			year--;
			semester++;
		} else {
			semester--;
		}
		period = year+ "/0" + semester;	
		return period;
	}
	
}
