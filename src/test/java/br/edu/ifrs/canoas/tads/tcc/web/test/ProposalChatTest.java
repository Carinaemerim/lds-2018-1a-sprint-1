package br.edu.ifrs.canoas.tads.tcc.web.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.tads.tcc.web.config.MyFluentTest;
import br.edu.ifrs.canoas.tads.tcc.web.page.ProposalChatPage;

public class ProposalChatTest extends MyFluentTest{
	@Page
    ProposalChatPage proposalChatPage;

    @Before
    public void login(){
        super.login();
    }
    
    @Test
    public void testMessageWithNoAttachment() {
    	//Given
    	proposalChatPage.go(port);
    	proposalChatPage.goToProposal();
    	proposalChatPage.isAt();
    	assertThat(proposalChatPage.getChatResults().displayed()).isTrue();

        //When
    	proposalChatPage.sendMessage("Teste Mensagem Proposta").awaitUntilSentMessageAppears();

        //Then
        assertThat(window().title()).isEqualTo("Gerenciamento de Documentos");
        assertThat(proposalChatPage.getFirstMessage().displayed()).isTrue();
        assertThat(proposalChatPage.getFirstMessage().text()).containsIgnoringCase("Teste Mensagem Proposta");
    }
}
