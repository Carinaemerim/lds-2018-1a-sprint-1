package br.edu.ifrs.canoas.tads.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.canoas.tads.tcc.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	Iterable<Task> findByPeriod(String period);

}
