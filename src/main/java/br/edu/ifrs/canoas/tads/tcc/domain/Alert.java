package br.edu.ifrs.canoas.tads.tcc.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public interface Alert {

	String title = "";
	String description="";
	Date date = null;
	boolean viewed = false;

}
