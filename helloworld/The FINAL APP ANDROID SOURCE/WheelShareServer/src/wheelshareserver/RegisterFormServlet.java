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

public class RegisterFormServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String userName = req.getParameter("userName");
		Key userNameKey = KeyFactory.createKey("Users", userName);
		try {
			Entity target = datastore.get(userNameKey);
		} catch (com.google.appengine.api.datastore.EntityNotFoundException e) {
			/* User not found, so we create new user */
			Entity user = new Entity("Users", userName);
			String password = req.getParameter("password");

			String gender = req.getParameter("gender");
			String creditCardNum = req.getParameter("creditCardNum");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");

			user.setProperty("password", password);
			user.setProperty("gender", gender);
			user.setProperty("creditCardNum", creditCardNum);
			user.setProperty("firstName", firstName);
			user.setProperty("lastName", lastName);
			user.setProperty("total rides", 0);
			user.setProperty("successful rides", 0);
			datastore.put(user);

		}
	}
}