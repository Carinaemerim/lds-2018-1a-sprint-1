package br.edu.ifrs.canoas.tads.tcc.service;

import br.edu.ifrs.canoas.tads.tcc.domain.*;
import br.edu.ifrs.canoas.tads.tcc.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.*;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * Created by cassiano on 3/21/18.
 */
@Service
@AllArgsConstructor
public class EvaluationService {


    TermPaperRepository termPaperRepository;
    EvaluationRepository evaluationRepository;
    AdviceRepository adviceRepository;
    GradeRepository gradeRepository;
    FileRepository fileRepository;
    TaskService taskService;
    AcademicYearRepository academicYearRepository;


    public Evaluation getOneEvaluation(Document document, User professor) {
        return evaluationRepository.findLastByDocumentIdAndAppraiserId(document.getId(), professor.getId());
    }


    public Advice getOne(Advice advice) {
        if (advice == null || advice.getId() == null)
            return null;
        Optional<Advice> optionalAdvice = adviceRepository.findById(advice.getId());
        return optionalAdvice.isPresent() ? optionalAdvice.get() : null;
    }

    public Grade getOne(Grade grade) {
        if (grade == null || grade.getId() == null)
            return null;
        Optional<Grade> optionalGrade = gradeRepository.findById(grade.getId());
        return optionalGrade.isPresent() ? optionalGrade.get() : null;
    }

    public Iterable<TermPaper> getTermPaperEvaluation(User user, Long academicYear) {
        final List<TermPaper> listWithDuplicates =  user != null ?
                termPaperRepository.getTermPaperForEvaluation(user.getId(), academicYear) : new ArrayList();

        if(listWithDuplicates != null) {
            List<TermPaper> listWithoutDuplicates =  listWithDuplicates.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(TermPaper::getId))),
                    ArrayList::new));
            return  listWithoutDuplicates;
        }
        return listWithDuplicates;
    }


    @Transactional
    public Advice saveThemeEvaluationFinal(Advice advice, Boolean isFinal) {
        Advice fetchedAdvice = (Advice)this.getOne(advice);
        if (fetchedAdvice == null || fetchedAdvice.getId() == null)
            fetchedAdvice = new Advice();
        fetchedAdvice.setConsiderations(advice.getConsiderations());
        fetchedAdvice.setStatus(advice.getStatus());
        fetchedAdvice.setIsFinal(isFinal);
        fetchedAdvice.setAppraiser(advice.getAppraiser());
        fetchedAdvice.setDocument(advice.getDocument());
        fetchedAdvice.setCreatedOn(new Date());
        return getOne(adviceRepository.save(fetchedAdvice));
    }

    @Transactional
    public Grade saveTermPaperEvaluationFinal(Grade grade, Boolean isFinal,  MultipartFile mFile, Boolean delFile) throws IOException {
        Grade fetchedGrade = (Grade)this.getOne(grade);
        File file;
        if (fetchedGrade == null || fetchedGrade.getId() == null)
            fetchedGrade = new Grade();

        if(delFile){
            if(fetchedGrade.getFile() != null){
                File oldFile = fileRepository.getOne(fetchedGrade.getFile().getId());
                evaluationRepository.setDeleteFile(fetchedGrade.getId());
                fileRepository.delete(oldFile);
            }
            mFile = null;
        }

        if(mFile!=null) {
            if(!mFile.isEmpty()) {

                if (fetchedGrade.getFile() == null)
                    file = new File();
                else
                    file = fileRepository.getOne(fetchedGrade.getFile().getId());

                file.setContent(mFile.getBytes());
                file.setFilename(mFile.getOriginalFilename());
                file.setContentType(mFile.getContentType());
                file.setCreatedOn(new Date());
                fileRepository.save(file);
                fetchedGrade.setFile(file);
            }
        }


        fetchedGrade.setConsiderations(grade.getConsiderations());
        //fetchedGrade.setStatus(grade.getStatus());
        fetchedGrade.setIsFinal(isFinal);
        fetchedGrade.setAppraiser(grade.getAppraiser());
        fetchedGrade.setDocument(grade.getDocument());

        fetchedGrade.setVocabulary(grade.getVocabulary());
        fetchedGrade.setDevelopmentInLogicalSequence(grade.getDevelopmentInLogicalSequence());
        fetchedGrade.setSubjectDomain(grade.getSubjectDomain());
        fetchedGrade.setAdequacyOfPresentation(grade.getAdequacyOfPresentation());
        fetchedGrade.setClosingExpectedTime(grade.getClosingExpectedTime());
        fetchedGrade.setPresentation(grade.getPresentation());
        fetchedGrade.setConclusion(grade.getConclusion());
        fetchedGrade.setExperiencedSolution(grade.getExperiencedSolution());
        fetchedGrade.setTheoreticalApproach(grade.getTheoreticalApproach());
        fetchedGrade.setMethodology(grade.getMethodology());
        fetchedGrade.setObjective(grade.getObjective());
        fetchedGrade.setLiteratureReview(grade.getLiteratureReview());
        fetchedGrade.setCreatedOn(new Date());

        return getOne(gradeRepository.save(fetchedGrade));
    }

    public Boolean getNextPeriod(AcademicYear academicYear) {
        String currentPeriod = academicYear.getTitle();
        AcademicYear ac;
        String next;
        for(int i=0; i<10; i++) {
            ac = (academicYearRepository.findFirstByTitle(taskService.next(currentPeriod)));
            if(ac != null) {
                System.out.println("NEXT: " + ac.toString());
                return true;
            }
            next = taskService.next(currentPeriod);
            currentPeriod = next;
        }

        System.out.println("NOT NEXT: ");
        return false;
    }
    public Boolean getPreviousPeriod(AcademicYear academicYear) {
        String currentPeriod = academicYear.getTitle();
        AcademicYear ac;
        String previous;
        for(int i=0; i<10; i++) {
            ac = (academicYearRepository.findFirstByTitle(taskService.previous(currentPeriod)));
            if(ac != null) {
                System.out.println("PREV: " + ac.toString());
                return true;
            }
            previous = taskService.previous(currentPeriod);
            currentPeriod = previous;
        }
        System.out.println("NOT PREV: ");
        return false;
    }
}
