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

public class UpdateRequestFormServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		String User = req.getParameter("userName");
		String PostID = req.getParameter("postID");
		String stateNum = req.getParameter("stateNum");
		// String userType = req.getParameter("userType");

	
		Transaction tr = datastore.beginTransaction();
		try {
			Key RequestKey = KeyFactory.createKey("Requests", User + "_"
					+ PostID);
			resp.setContentType("text");
			PrintWriter out = resp.getWriter();
			out.print(User + "_" + PostID);
			Entity target = datastore.get(RequestKey);
			target.setProperty("State", Integer.parseInt(stateNum));
			datastore.put(target);
			//Key postKey = KeyFactory.createKey("Posts", Long.parseLong(PostID));
			//DatastoreService datastore2 = DatastoreServiceFactory
					//.getDatastoreService();

			//Entity post = datastore2.get(postKey);
			//Integer temp = (Integer) post.getProperty("seats");
			//--temp;
			//post.setProperty("seats", 10);
			//datastore.put(post);

			tr.commit();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	// return multipul requests error;

}