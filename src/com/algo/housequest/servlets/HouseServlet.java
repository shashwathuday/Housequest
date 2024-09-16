package com.algo.housequest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algo.housequest.models.House;
import com.google.gson.Gson;

@WebServlet("/HouseServlet")
public class HouseServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public HouseServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int destId = Integer.parseInt(request.getParameter("destId"));
			List<House> houseList = new ArrayList<>();
			houseList = dbUtil.getHouse(destId);

			String houseJsonString = new Gson().toJson(houseList);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(houseJsonString);
			out.flush();
		} catch (Exception exec) {
			throw new ServletException(exec);             
		}
	}
}
