package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
        Iterator<Entry<String, List<String>>> entries = map.entrySet().iterator();  
        StringBuffer sb = new StringBuffer();
        
        List<String> list ;
        while (entries.hasNext()) { 
            Map.Entry<String, List<String>> entry = entries.next();  
            sb.append("<div class=\"form-group\"> <label class=\"col-sm-4 control-label\" for=\""
            + entry.getKey()+"\">"+ entry.getKey()+ ": </label><div class=\"col-sm-8\">"
                    + "<select class=\"form-control\" id=\""+entry.getKey()
                    +"\" name=\""+entry.getKey()+"\">");
            list = entry.getValue();
            for(String val: list){
                sb.append("<option value=\""+ val+ "\">" + val+ "</option>");
            }
            
            sb.append("</select> </div></div>");
        }  
        request.setAttribute("data", sb.toString());
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
        
        request.setAttribute("model", model);
        StringBuffer sb = new StringBuffer();
        for(String[] row: list){
            sb.append("<tr><td>"+ row[0]+"</td><td>"+ row[1]+"</td> <td>"+ row[2]+"</td></tr>");
        }
        request.setAttribute("list", sb.toString());
       
        
        String address = "/showChoice.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
        
	}

}
