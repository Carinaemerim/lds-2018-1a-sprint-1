package br.edu.ifrs.canoas.tads.tcc.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import br.edu.ifrs.canoas.tads.tcc.config.Messages;
import br.edu.ifrs.canoas.tads.tcc.domain.AcademicYear;
import br.edu.ifrs.canoas.tads.tcc.domain.TermPaper;
import br.edu.ifrs.canoas.tads.tcc.service.AcademicYearService;
import br.edu.ifrs.canoas.tads.tcc.service.DocumentService;
import br.edu.ifrs.canoas.tads.tcc.service.EvaluationService;
import br.edu.ifrs.canoas.tads.tcc.service.FileService;
import br.edu.ifrs.canoas.tads.tcc.service.MessageService;
import br.edu.ifrs.canoas.tads.tcc.service.TermPaperService;
import br.edu.ifrs.canoas.tads.tcc.service.UserService;

@WebMvcTest(DocumentController.class)
public class DocumentControllerTest extends BaseControllerTest {

	@MockBean
	Messages messages;
	@MockBean
	TermPaperService termPaperService;
	@MockBean
	UserService userService;
	@MockBean
	DocumentService documentService;
	@MockBean
	MessageService messageService;
	@MockBean
	AcademicYearService academicYearService;
	@MockBean
	EvaluationService evaluationService;
	@MockBean
	FileService fileService;

	AcademicYear academicYear;
	TermPaper termPaper;

	//TODO nicolas.w
	@Before
	public void setupInternal() {
		academicYear = new AcademicYear();
		academicYear.setId(1L);
		academicYear.setTitle("2018/01");
		termPaper = new TermPaper();
		Mockito.when(
				academicYearService.getAcademicYearByIdOrPeriod(Optional.ofNullable(null), Optional.ofNullable(null)))
				.thenReturn(academicYear);
		Mockito.when(termPaperService.getOneByUserAndAcademicYear(userDetails.getUser(), academicYear))
				.thenReturn(termPaper);
	}

	//TODO nicolas.w
	@Test
	public void view_term_paper() throws Exception {
		this.mvc.perform(get("/document/").with(user(userDetails)).accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk()).andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(view().name("/document/document"));
	}
}