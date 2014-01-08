package wheelshareserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;

import com.google.appengine.api.datastore.Query.FilterOperator;

public class GetRequestFormServlet extends HttpServlet {
	final static int LIST_SIZE_LIMIT = 50;
	private Filter requesterFilter, posterFilter, userNameFilter;
	private Query query;
	private Key userNameKey;
	private Entity post, requesterUser;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		/* response type and declaration */
		resp.setContentType("text");
		PrintWriter out = resp.getWriter();

		String userName = req.getParameter("userName");

		/* Filters for search. Searches Entity Posts: property */
		requesterFilter = new FilterPredicate("Requester Username",
				FilterOperator.EQUAL, userName);

		posterFilter = new FilterPredicate("Poster Username",
				FilterOperator.EQUAL, userName);

		userNameFilter = CompositeFilterOperator.or(posterFilter,
				requesterFilter);

		query = new Query("Requests").setFilter(userNameFilter);
		/* The datastore returns a list of entity */
		List<Entity> requests = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(LIST_SIZE_LIMIT));

		for (Entity request : requests) {
			userNameKey = KeyFactory.createKey("Posts",
					Long.parseLong(((String) request.getProperty("postID"))));

			try {
				post = datastore.get(userNameKey);
				requesterUser = datastore.get(KeyFactory.createKey("Users",
						(String) request.getProperty("Requester Username")));
				out.print(request.getProperty("State") + "_");
				out.print(post.getProperty("postID") + "_");
				// if user is poster
				if (userName.equals(request.getProperty("Poster Username"))) {
					out.print("poster" + "_");
					out.print(requesterUser.getProperty("total rides") + "_");
					out.print(requesterUser.getProperty("successful rides")
							+ "_");
					out.print(post.getProperty("userType") + "_");
					out.print(post.getProperty("destination") + "_");
					out.print(post.getProperty("fare") + "_");
					out.print(post.getProperty("source") + "_");
					out.print(post.getProperty("date") + "_");
					out.print(request.getProperty("Requester Username") + "_");
					out.print(requesterUser.getProperty("firstName") + "_");
					out.print(requesterUser.getProperty("lastName") + "_");
					out.print(requesterUser.getProperty("gender") + "_");
					out.print(post.getProperty("seats") + "_");
					out.print(post.getProperty("description") + "_");
				}
				// if user is requester
				else if (userName.equals(request
						.getProperty("Requester Username"))) {
					out.print("requester" + "_");
					out.print(post.getProperty("total rides") + "_");
					out.print(post.getProperty("successful rides") + "_");
					out.print(post.getProperty("userType") + "_");
					out.print(post.getProperty("destination") + "_");
					out.print(post.getProperty("fare") + "_");
					out.print(post.getProperty("source") + "_");
					out.print(post.getProperty("date") + "_");
					out.print(post.getProperty("userName") + "_");
					out.print(post.getProperty("firstName") + "_");
					out.print(post.getProperty("lastName") + "_");
					out.print(post.getProperty("gender") + "_");
					out.print(post.getProperty("seats") + "_");
					out.print(post.getProperty("description") + "_");
				}

			} catch (EntityNotFoundException e) {
				out.print("POST IS NULL");
				e.printStackTrace();
			}
		}

	}
}
