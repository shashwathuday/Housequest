package com.algo.housequest.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.algo.housequest.utils.DbUtil;


/**
 * Servlet implementation class ZephyrServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil;

	// Tomcat will inject the connection pool object to the dataSource Variable
	@Resource(name = "jdbc/house_quest")
	private DataSource dataSource;

	// overriding the init() method to create an instance of the student db
	@Override
	public void init() throws ServletException {
		dbUtil = new DbUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/homeQuest.jsp");
		dispatcher.forward(request, response);

	}

}
