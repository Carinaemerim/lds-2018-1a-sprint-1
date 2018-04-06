package br.edu.ifrs.canoas.tads.tcc.web.page;

import lombok.Data;

@Data
public class ProposalChatPage extends ChatPage {
	public ProposalChatPage goToProposal() {
		proposalTab.click();
		return this;
	}
}
