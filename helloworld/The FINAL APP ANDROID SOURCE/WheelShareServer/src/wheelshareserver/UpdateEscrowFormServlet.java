package wheelshareserver;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateEscrowFormServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String stateNum = req.getParameter("stateNum");
		String amount = req.getParameter("amount");

		Transaction tr = datastore.beginTransaction();
		try {
			Key EscrowKey = KeyFactory.createKey("Escrow", "led_beamer");
			Entity target = datastore.get(EscrowKey);
			int temp;
			if (stateNum.equals("3")) {
				temp = Integer.parseInt(target.getProperty("Funds").toString());
				target.setProperty("Funds", temp + Integer.parseInt(amount));
			} else {
				temp = Integer.parseInt(target.getProperty("Funds").toString());
				target.setProperty("Funds", temp - Integer.parseInt(amount));
				double dtemp = Double.parseDouble(target.getProperty("Profit")
						.toString());

				dtemp += .1 * Integer.parseInt(amount);
				DecimalFormat df = new DecimalFormat("#.##");

				target.setProperty("Profit", (df.format(dtemp)));
				temp = Integer.parseInt(target.getProperty("#Transaction")
						.toString());
				target.setProperty("#Transaction", temp + 1);
			}

			datastore.put(target);

			tr.commit();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
