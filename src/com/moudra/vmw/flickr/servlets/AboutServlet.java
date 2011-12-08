package com.moudra.vmw.flickr.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AboutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/about.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
