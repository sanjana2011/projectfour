package in.co.rays.Proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.Proj4.util.ServletUtility;
/**
 * @author Sanjana Gangrade
 *
 */
@WebServlet ("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletUtility.forward(getView(), request, response);
	}
	@Override
	protected String getView() {
		
		return ORSView.WELCOME_VIEW ;
	}
	}

