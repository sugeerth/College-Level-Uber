package wheelshareserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class LoginFormServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text");
		PrintWriter out = resp.getWriter();

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");

		Key userNameKey = KeyFactory.createKey("Users", userName);
		try {
			Entity user = datastore.get(userNameKey);
			if (!password.equals(user.getProperty("password"))) {
				out.print("FAIL");
				return;
			}
			out.print("SUCCESS" + "_" + user.getProperty("firstName") + "_"
					+ user.getProperty("lastName") + "_"
					+ user.getProperty("gender") + "_"
					+ user.getProperty("creditCardNum") + "_"
					+ user.getProperty("total rides") + "_"
					+ user.getProperty("successful rides") + "_");
		} catch (com.google.appengine.api.datastore.EntityNotFoundException e) {
			out.print("FAIL");
		}

	}
}
