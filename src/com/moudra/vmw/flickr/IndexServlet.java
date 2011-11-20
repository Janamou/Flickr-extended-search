package com.moudra.vmw.flickr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
