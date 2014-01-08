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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRatingFormServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String User = req.getParameter("userName");
		String rating = req.getParameter("rating");

		Transaction tr = datastore.beginTransaction();
		try {
			Key postKey = KeyFactory.createKey("Users", User);

			Entity target = datastore.get(postKey);
			int temp = Integer.parseInt(target.getProperty("total rides")
					.toString());
			target.setProperty("total rides", temp + 1);
			if (rating.equals("1")) {
				temp = Integer.parseInt(target.getProperty("successful rides")
						.toString());
				target.setProperty("successful rides", temp + 1);
			}
			datastore.put(target);

			tr.commit();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// return multipul requests error;

}