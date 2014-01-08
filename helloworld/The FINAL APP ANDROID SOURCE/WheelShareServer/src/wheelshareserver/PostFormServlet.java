package wheelshareserver;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class PostFormServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		/* Get parameter from android */
		String destination = req.getParameter("destination");
		String source = req.getParameter("source");
		int fare = Integer.parseInt(req.getParameter("fare"));
		String date = req.getParameter("date");
		String userType;// = req.getParameter("userType").equals("rider") ? "driver" : "rider";
		if (req.getParameter("userType").equals("rider"))
			userType = "driver";
		else
			userType = "rider";
		String userName = req.getParameter("userName");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String gender = req.getParameter("gender");
		int totalRides = Integer.parseInt(req.getParameter("total rides"));
		int successfulRides = Integer.parseInt(req
				.getParameter("successful rides"));

		long postID;

		// String creditCardNum = req.getParameter("creditCardNum");
		int seats = Integer.parseInt(req.getParameter("seats"));
		String description = req.getParameter("description");

		//if (!destination.equals("")) {
			/* Create a new post and set the property */
			Entity post = new Entity("Posts");

			post.setProperty("destination", destination);
			post.setProperty("source", source);
			post.setProperty("date", date);
			post.setProperty("fare", fare);
			post.setProperty("seats", seats);
			post.setProperty("userType", userType);
			post.setProperty("userName", userName);
			post.setProperty("firstName", firstName);
			post.setProperty("lastName", lastName);
			post.setProperty("gender", gender);
			// post.setProperty("creditCardNum", creditCardNum);
			post.setProperty("description", description);
			post.setProperty("total rides", totalRides);
			post.setProperty("successful rides", successfulRides);

			/* Add to datastore */
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			datastore.put(post);

			// get post ID from key
			postID = post.getKey().getId();
			post.setProperty("postID", postID);
			datastore.put(post);

		//}

	}

}
