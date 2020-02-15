package Schedulers;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import CallMatches.Commands;
import ConnectionDB.Queries;
import DAO.User;

/**
 * This is a sample class to execute scheduler on specific date based on <code>java.util.Calendar</code>. Over here,
 * <code>executor</code> is a runnable which run on everyday basis from 8:00 AM to 5:00 PM.
 * 
 * @author Tomas Mali
 */
public class Builder {

	public static Commands mySianConnect = null;
	public static Update update = null;

	static String attention = "\u26a0\ufe0f";
	static String ok_check = "\u2714\ufe0f";

	public static Long mio = (long) 145645559;
	private static String icon_link = "\ud83d\udcce";
	private static String icon_clock = "\u231a\ufe0f";

	public Builder(Commands instance) {
		mySianConnect = instance;

	}

	/**
	 * Create the keyboard and send it to the user
	 */
	public static void CreateKeyboard(Long idTelegram, boolean isMismatch) {

		final String msg = isMismatch
				? "Commando non riconosciuto! Usare il menu sottostante per effettuare una operazione"
				: "Un utente amministratore ha accettato la tua richiesta, adesso puoi utilizzare il Bot";

		SendMessage message = ComposeMessage(msg, !isMismatch ? idTelegram : null);

		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow row = new KeyboardRow();
		User user = Queries.getUserIfExsist(idTelegram);

		// Admin users
		if (user != null && user.getAdmin()) {
			row.add("\ud83d\udcce Pending");
			row.add("\u231a\ufe0f Send Document");
			row.add("Pending users");
			keyboard.add(row);

		}
		// Normal users
		else if (user != null && !user.getAdmin()) {
			row = new KeyboardRow();
			row.add("Row 2 Button 1");
			row.add("Row 2 Button 2");
			row.add("Row 2 Button 3");
			keyboard.add(row);
		}

		keyboardMarkup.setKeyboard(keyboard);
		message.setReplyMarkup(keyboardMarkup);
		SendMessage(message);
	}

	/**
	 * Compose a message passing the message text and the telegramId
	 * 
	 * @param msg
	 * @param chatId
	 */
	public static SendMessage ComposeMessage(String msg, Long chatId) {
		if (chatId == null)
			chatId = update.getMessage().getChatId() == null ? update.getCallbackQuery().getMessage().getChatId()
					: update.getMessage().getChatId();
		return new SendMessage().setChatId(chatId).setText(msg);
	}

	/**
	 * Send the message msg
	 * 
	 * @param msg
	 */
	public static void SendMessage(SendMessage msg) {
		try {
			mySianConnect.execute(msg);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
