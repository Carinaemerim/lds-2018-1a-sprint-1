package br.edu.ifrs.canoas.tads.tcc.service;


import br.edu.ifrs.canoas.tads.tcc.domain.Document;
import br.edu.ifrs.canoas.tads.tcc.domain.EvaluationBoard;

import br.edu.ifrs.canoas.tads.tcc.repository.EvaluationBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;




@Service
@AllArgsConstructor
public class EvaluationBoardService {

    EvaluationBoardRepository evaluationBoardRepository;

    public EvaluationBoard getOneByDocumentId(Long documentId) {
        return evaluationBoardRepository.findFirstByDocumentId(documentId);
    }

	public EvaluationBoard save(EvaluationBoard evaluationBoard) {
		evaluationBoardRepository.save(evaluationBoard);
		return evaluationBoardRepository.save(evaluationBoard);
		
	}
}
