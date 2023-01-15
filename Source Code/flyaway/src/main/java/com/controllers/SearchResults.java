package com.controllers;

import java.io.IOException;
import java.util.List;

import com.dao.FlightDetailsDao;
import com.dao.FlightDetailsDaoImpl;
import com.dto.FlightDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchResults() {
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		String date = request.getParameter("date");
		String travellers = request.getParameter("travellers");
		
		HttpSession session = request.getSession();
		session.setAttribute("source", source);
		session.setAttribute("destination", destination);
		session.setAttribute("date", date);
		session.setAttribute("travellers", travellers);
		
		
		FlightDetailsDao dao = new FlightDetailsDaoImpl();
		List<FlightDetails> flights = dao.searchFlights(source, destination);
		session.setAttribute("flights", flights);
		response.sendRedirect("search-results.jsp");
	}

}
