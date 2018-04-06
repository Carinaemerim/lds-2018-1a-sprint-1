package br.edu.ifrs.canoas.tads.tcc.web.page;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.concurrent.TimeUnit;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import lombok.Data;

@PageUrl("http://localhost:{port}/document")
@Data
public class ChatPage extends FluentPage {
	@FindBy(css = "#send-button")
    protected FluentWebElement sendButton;
	
	@FindBy(css = "#monograph-tab")
	protected FluentWebElement monographTab;
	
	@FindBy(css = "#proposal-tab")
	protected FluentWebElement proposalTab;
	
	@FindBy(css = "#message-0")
	protected FluentWebElement firstMessage;
	
	@FindBy(css = "#chat-results")
	protected FluentWebElement chatResults;

    public void isAt() {
        assertThat(window().title()).isEqualTo("Gerenciamento de Documentos");
    }

    public ChatPage sendMessage(String message) {
        $("#chat-input").fill().with(message);
        sendButton.click();
        return this;
    }
    
    public ChatPage awaitUntilSentMessageAppears() {
        await().atMost(5, TimeUnit.SECONDS).until(firstMessage).present();
        return this;
    }
}
