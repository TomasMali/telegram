package Tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Tests {

	public static void main(String[] args) throws IOException { // Fri Nov 23 14:34:18 CET 2018
																// Guida-chiusura-campagna_v2 Wed, 02 Aug 2017 13:57:42
																// GMT

		System.out.println("Done");

		String urlR = "https://www.sian.it/public/mipaaf/Matrice-schede-operazioni-controlli%202%20liv%20v4.7.zip";

		URL url_1 = new URL(urlR);
		HttpURLConnection http_1 = (HttpURLConnection) url_1.openConnection();

		String dataModifica = new Date(url_1.openConnection().getLastModified()).toString();

		System.out.println(dataModifica);

		// try {
		// Calendar cal = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		//
		// String string1 = "09:40:00";
		// Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
		// Calendar calendar1 = Calendar.getInstance();
		// calendar1.setTime(time1);
		//
		// String string2 = "20:00:00";
		// Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
		// Calendar calendar2 = Calendar.getInstance();
		// calendar2.setTime(time2);
		// calendar2.add(Calendar.DATE, 1);
		//
		// String someRandomTime = sdf.format(cal.getTime());
		// Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
		// Calendar calendar3 = Calendar.getInstance();
		// calendar3.setTime(d);
		// calendar3.add(Calendar.DATE, 1);
		//
		// Date x = calendar3.getTime();
		// if (x.after(calendar1.getTime()) && x.before(calendar2.getTime()))
		// System.out.println(true);
		//
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }

	}

}
