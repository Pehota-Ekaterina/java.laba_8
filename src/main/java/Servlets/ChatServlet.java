package Servlets;

import java.util.ArrayList; 
import java.util.HashMap; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import Entity.ChatMessage; 
import Entity.ChatUser; 
 
public class ChatServlet extends HttpServlet { 
	

	public HashMap<String, ChatUser> activeUsers; 
	public ArrayList<ChatMessage> messages;
  
 
  
public void init() throws ServletException { 
    super.init(); 
   
    activeUsers = (HashMap<String, ChatUser>) getServletContext().getAttribute("activeUsers"); 
    messages = (ArrayList<ChatMessage>) getServletContext().getAttribute("messages"); 

    if (activeUsers==null) { 
      activeUsers = new HashMap<String, ChatUser>(); 
      getServletContext().setAttribute("activeUsers", activeUsers);       
    } 
    
    if (messages==null) { 
      messages = new ArrayList<ChatMessage>(100); 
      getServletContext().setAttribute("messages", messages); 
    } 
  }         
} 
