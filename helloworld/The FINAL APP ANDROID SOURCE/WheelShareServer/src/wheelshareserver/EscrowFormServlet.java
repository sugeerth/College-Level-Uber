package wheelshareserver;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EscrowFormServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity Escrow = new Entity("Escrow", "led_beamer");

		Escrow.setProperty("Funds", 0);
		Escrow.setProperty("Profit", "0.0");
		Escrow.setProperty("#Transaction", 0);
		datastore.put(Escrow);
	}
}