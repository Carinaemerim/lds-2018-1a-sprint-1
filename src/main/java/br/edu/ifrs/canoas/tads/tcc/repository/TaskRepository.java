package br.edu.ifrs.canoas.tads.tcc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{

	Iterable<Task> findByPeriod(String period);

}
