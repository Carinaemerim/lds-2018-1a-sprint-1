package br.edu.ifrs.canoas.tads.tcc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;

@Repository
public interface ScheduleRepository extends CrudRepository<Task, Long>{

}
