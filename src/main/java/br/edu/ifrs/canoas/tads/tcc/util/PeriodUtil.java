package br.edu.ifrs.canoas.tads.tcc.util;

import java.util.Calendar;

import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;

public class PeriodUtil {

	public static String getCurrentPeriod() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		String period = String.valueOf(ano);
		if (Calendar.getInstance().get(Calendar.MONTH) < 7) {
			period = period + "/01";
		} else {
			period = period + "/02";
		}
		return period;
	}

	public static boolean isCurrentPeriod(AcademicYear academicYear) {
		return (academicYear != null && academicYear.getTitle().equals(getCurrentPeriod()));
	}
}
