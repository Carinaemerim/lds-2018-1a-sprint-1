package br.edu.ifrs.canoas.tads.tcc.web.page;

import lombok.Data;

@Data
public class MonographChatPage extends ChatPage{
    public MonographChatPage goToMonograph() {
    	monographTab.click();
    	return this;
    }
}
