package br.edu.ifrs.canoas.tads.tcc.service;

import org.springframework.stereotype.Service;

import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationBoard;
import br.edu.ifrs.canoas.tads.tcc.repository.EvaluationBoardRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssessmentBankService {
	
    EvaluationBoardRepository evaluationBoardRepository;


	public EvaluationBoard save(EvaluationBoard evaluationBoard) {
		evaluationBoardRepository.save(evaluationBoard);
		return evaluationBoardRepository.save(evaluationBoard);		
	}

}
