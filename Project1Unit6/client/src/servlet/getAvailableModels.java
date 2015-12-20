package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import client.*;
/**
 * Servlet implementation class getAvailableModels
 */
@WebServlet("/getAvailableModels")
public class getAvailableModels extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAvailableModels() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	   
	    DefaultSocketClient c = new DefaultSocketClient("localhost", 12345, "3");
	    c.start();
	    
	    boolean sockIsAlive = true;

        do {
            if(sockIsAlive && !c.isAlive()) {
                sockIsAlive = false;
                System.out.println("sock is dead.");
            }

        } while(sockIsAlive);
	    
        System.out.println(c.getResult());
        String[] data = ((String)c.getResult()).split(", ");
        
	    request.setAttribute("list", data);
	    request.setAttribute("test", "mytest");
	    String address = "/getModels.jsp";
	    RequestDispatcher dispatcher =
	    request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	}

}
