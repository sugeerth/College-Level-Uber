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
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;

import com.google.appengine.api.datastore.Query.FilterOperator;

public class SearchFormServlet extends HttpServlet {
	final static int LIST_SIZE_LIMIT = 10;
	private Filter destFilter, sourceFilter, userTypeFilter;
	private Query query;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String destination = req.getParameter("destination");
		String source = req.getParameter("source");
		String userType = req.getParameter("userType");
		String date = req.getParameter("date");
		String fare = req.getParameter("fare");
		// int fareint = fare == null ? Integer.MAX_VALUE:
		// Integer.parseInt(fare);

		/* Filters for search. Searches Entity Posts: property */
		destFilter = new FilterPredicate("destination", FilterOperator.EQUAL,
				destination);
		sourceFilter = new FilterPredicate("source", FilterOperator.EQUAL,
				source);
		userTypeFilter = new FilterPredicate("userType", FilterOperator.EQUAL,
				userType);

		// if all fields are null

		Filter defaultfilter = CompositeFilterOperator.and(destFilter,
				sourceFilter, userTypeFilter);

		if (!date.equals("")) {
			Filter dateFilter = new FilterPredicate("date",
					FilterOperator.EQUAL, date);
			defaultfilter = CompositeFilterOperator.and(defaultfilter,
					dateFilter);
		}

		if (!fare.equals("")) {
			Filter fareFilter = new FilterPredicate("fare",
					FilterOperator.LESS_THAN_OR_EQUAL, Integer.parseInt(fare));
			defaultfilter = CompositeFilterOperator.and(defaultfilter,
					fareFilter);
		}

		query = new Query("Posts").setFilter(defaultfilter);
		/* The datastore returns a list of entity */
		List<Entity> posts = datastore.prepare(query).asList(
				FetchOptions.Builder.withLimit(LIST_SIZE_LIMIT));

		/* response type and declaration */
		resp.setContentType("text");
		PrintWriter out = resp.getWriter();

		for (Entity post : posts) {
			out.print(post.getProperty("postID") + "_");
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

	}
}
