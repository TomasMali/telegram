package CallMatches;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import ConnectionDB.Queries;
import DAO.User;
import Schedulers.Builder;

public class Commands extends TelegramLongPollingBot {

	public boolean partito = false;

	@Override
	public String getBotToken() {

		return "676793933:AAFSqroVLFsRsYU1nk12-gmVWrYprDN2q-I";
	}

	public String getBotUsername() {
		return "TomasMali";
		// tomasmalibot
		// 502596920:AAGXj1omTxPldCElns1Wiw965LqslMSKBHw
		// TomasSubitoBot
		// 657809545:AAEA4xHiTKLndDuRJc9G5XYrwt-ul2WFqH0
		// tomasweather
		// 601333146:AAHJ4Fa1wDt5x5Tsm2bB7CQE1qhYAEXxyBM
		// siandocstatus
		// 645382473:AAG1Vtkoky27VLINnIWRvaxQxqig-xsbKa4
		// Test2TomasBot
		// 676793933:AAFSqroVLFsRsYU1nk12-gmVWrYprDN2q-I

	}

	@Override
	public void onUpdateReceived(Update update) {
		Builder.update = update;

		if (update.hasMessage())
			hasMessage(update);
		else if (update.hasCallbackQuery())
			HasCallbackQuery(update);

	}

	/**
	 * Controlla tutti i messaggi con testo che arrivano e fa il parsing
	 * 
	 * @param update
	 */
	public void hasMessage(Update update) {

		// Search if the user exists first

		if (update.getMessage().hasText()) {

			String text = update.getMessage().getText();
			System.out.println("### User send: " + text);
			// Check to see if the user is not in pending state
			User user = Queries.getUserIfExsist(update.getMessage().getChatId());
			if (user != null) {
				if (user.getPending()) {
					Builder.SendMessage(Builder.ComposeMessage(
							"Sei ancora in attesa che un utente amministratore ti dia il consenso.", null));
					return;
				}
			} else {
				// if the user access is the fist one
				if (!text.toUpperCase().equals("/START")) {
					Builder.SendMessage(Builder.ComposeMessage("Non sei ancora registrato, registrati con /start .",
							null));
					return;
				}
			}

			switch (text) {
			case "/start":
			case "/Start":
			case "/START":
				CommandsMatches.welcome(update);
				break;
			case "ciao":
				System.out.println(Queries.getAdminUsers());
				// System.out.println(Queries.getAdminUsers());
				break;

			case "\u231a\ufe0f Send Document":
				CommandsMatches.SendDocument(this, update);
				break;
			case "\ud83d\udcce Pending":
				CommandsMatches.Pendings(this, update);
				break;

			default:
				Builder.CreateKeyboard(update.getMessage().getChatId(), true);
				break;
			}

		}

	}

	public void HasCallbackQuery(Update update) {

		Long adminUser = update.getCallbackQuery().getMessage().getChatId();
		// Check to see if the user is not in pending state
		User user = Queries.getUserIfExsist(adminUser);

		String callback = update.getCallbackQuery().getData();
		String substringCommand = callback.substring(0, 7);

		if (user != null) {
			if (user.getPending()) {
				Builder.SendMessage(Builder.ComposeMessage(
						"Sei ancora in attesa che un utente amministratore ti dia il consenso.", adminUser));
				return;
			}
		} else {
			// if the user access is the fist one
			if (!callback.toUpperCase().equals("/START")) {
				Builder.SendMessage(Builder.ComposeMessage("Non sei ancora registrato, registrati con \\start .",
						adminUser));
				return;
			}
		}

		System.out.println(substringCommand);

		switch (substringCommand) {
		case "pending":
			CommandsMatches.RegisterUserInPending(Long.parseLong(callback.substring(7)), adminUser);
			break;

		default:
			break;
		}

		if (callback.substring(0, 10).equals("white_list"))
			try {

				execute(new SendMessage().setChatId(adminUser).setText("Tu hai clickatpo boh: " + substringCommand));
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		// keyboard per abilitarsi nei links
		// CommandsMatches.createAbilitazione(this, update, Queries.getLinkId(update.getCallbackQuery().getData()));

	}

}
