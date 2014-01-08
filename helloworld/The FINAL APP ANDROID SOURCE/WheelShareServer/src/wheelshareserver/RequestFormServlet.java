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

public class RequestFormServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String requesterUserName = req.getParameter("Requester Username");
		String PostID = req.getParameter("postID");
		String posterUserName = req.getParameter("Poster Username");
		Key RequestKey = KeyFactory.createKey("Requests", requesterUserName
				+ "_" + PostID);
		try {
			// if request key exists, that means this user has alrdy requested
			// this post so we throw error for re request
			Entity target = datastore.get(RequestKey);
		} catch (com.google.appengine.api.datastore.EntityNotFoundException e) {
			Entity Request = new Entity("Requests", requesterUserName + "_"
					+ PostID);
			Request.setProperty("Requester Username", requesterUserName);
			Request.setProperty("Poster Username", posterUserName);
			Request.setProperty("State", 1);
			Request.setProperty("New Post", "true");
			Request.setProperty("postID", PostID);
			datastore.put(Request);
		}
	}
}