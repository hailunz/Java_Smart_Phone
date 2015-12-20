package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.DefaultSocketClient;
import adapter.*;
import model.*;
import scale.*;

/**
 * Servlet implementation class getModel
 */
@WebServlet("/getModel")
public class getModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getModel() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("getModel");
        System.out.println(request.getSession());
        String modelname = (String) request.getParameter("Model");
        System.out.println(modelname+"!");
        DefaultSocketClient c = new DefaultSocketClient("localhost", 12345, "4");
        c.setModelName(modelname);
        c.start();
        
        boolean sockIsAlive = true;

        do {
            if(sockIsAlive && !c.isAlive()) {
                sockIsAlive = false;
                System.out.println("sock is dead.");
            }

        } while(sockIsAlive);
        
        Automobile model = (Automobile) c.getResult();
        BuildAuto ba = new BuildAuto();
        ba.addAuto(model.getName(),model);
        request.getSession().setAttribute("model", model);
        
        request.setAttribute("model", model);
        HashMap<String, List<String>> map = model.getOptionSetString();
        request.setAttribute("map", map);
        System.out.println(map.keySet().toString());
        String address = "/showModel.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("postModel");
	    System.out.println(request.getSession());
        Automobile model = (Automobile)request.getSession().getAttribute("model");
        System.out.println(model.getName());
        Map<String, String[]> map = request.getParameterMap();
        for(String name: map.keySet()){
            System.out.println(name);
            model.setOptionChoice(name, map.get(name)[0]);
        }
        
        List<String[]> list = new ArrayList<String[]> ();
        list.add(new String[]{model.getName(), "base price", 
                String.valueOf(model.getBaseprice())});
        
        for(String name: map.keySet()){
            list.add(new String[]{name, model.getOptionChoice(name),
                    String.valueOf(model.getOptionChoicePrice(name))});
        }
        
        list.add(new String[]{"<strong>Total Cost</strong>","", "<strong>$"
        +String.valueOf(model.getTotalPrice())+"</strong>"});
        
        request.setAttribute("list", list);
        request.setAttribute("model", model);
       
        
        String address = "/showChoice.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
        
	}

}
