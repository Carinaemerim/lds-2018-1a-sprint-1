package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.util.CollectionUtils;

import lombok.Data;

/**
 * Created by rodrigo on 2/21/17.
 */
@Data
@Entity
public class Professor extends User {

	private String lattes;
	private String experience;
	private String skill;
	@OneToMany(mappedBy = "advisor", fetch = FetchType.EAGER)
	private List<TermPaper> termPapers;
	@ManyToMany
	private List<EvaluationBoard> board;

	public String getCurrentPeriod() {
		String period = "";
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		period = String.valueOf(ano);
		if (Calendar.getInstance().get(Calendar.MONTH) < 7) {
			period = period + "/01";
		} else {
			period = period + "/02";
		}
		return period;
	}

	@Transient
	public Integer getCurrentPeriodApprovedThemesNumber() {
		//TODO Nicolas
		if (CollectionUtils.isEmpty(this.termPapers)) {
			return 0;
		} else {
			int num = 0;
			for (TermPaper paper : this.termPapers) {
				if (paper.getThemeDocument() != null && paper.getThemeDocument().getStatus() != null
						&& paper.getThemeDocument().getStatus().equals(EvaluationStatus.APPROVED)
						&& paper.getAcademicYear() != null && paper.getAcademicYear().getTitle() != null
						&& paper.getAcademicYear().getTitle().equals(getCurrentPeriod()))
					num++;
			}
			return num;
		}
	}
}