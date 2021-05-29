package Servlets;


import java.io.IOException; 
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date; 
import javax.servlet.ServletException; 
import javax.servlet.http.Cookie; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import Entity.ChatMessage;
import Entity.ChatUser;

public class LoginServlet extends ChatServlet { 
 
 private Date date = new Date();
 private int KolCh = 2; 
 private int sessionTimeout = 2; 
       
 
 
 public void init() throws ServletException { 
   super.init(); 
   String value = getServletConfig().getInitParameter("SESSION_TIMEOUT"); 
   if (value!=null) { 
     sessionTimeout = Integer.parseInt(value); 
   } 
 } 
 
 
 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		 throws ServletException, IOException { 
	 
   String name = (String)request.getSession().getAttribute("name"); 
   String errorMessage = (String)request.getSession().getAttribute("error");
   
   
   String previousSessionId = null;  
   if (name==null) { 
     for (Cookie aCookie: request.getCookies()) { 
       if (aCookie.getName().equals("sessionId")) { 
         previousSessionId = aCookie.getValue(); 
         break; 
       } 
     } 
     if (previousSessionId!=null) {  
       for (ChatUser aUser: activeUsers.values()) { 
         if (aUser.getSessionId().equals(previousSessionId)) { 
           name = aUser.getName(); 
           aUser.setSessionId(request.getSession().getId()); 
         } 
       }         
     }       
   }    
   
   
   if (name!=null && !"".equals(name)) { 
     errorMessage = processLogonAttempt(name, request, response); 
   }  
   
   
   response.setCharacterEncoding("utf8"); 
   PrintWriter pw = response.getWriter(); 
   pw.println("<html><head><title>����-���!</title><meta http-" +
   		"equiv='Content-Type' content='text/html; charset=utf-8'/></head>"); 
   if (errorMessage!=null) { 
     pw.println("<p><font color='red'>" + errorMessage + "</font></p>"); 
   }
   pw.println("<form action='/Laba_8/' method='post'>������� ���:" +
   "<input type='text' name='name' value=''><input type='submit' value='��������� " + "'>");
   pw.println("</form></body></html>"); 
   request.getSession().setAttribute("error", null); 
 } 

 
 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		 throws ServletException, IOException { 
   request.setCharacterEncoding("UTF-8"); 
   String name = (String)request.getParameter("name"); 
   String errorMessage = null; 
   
   
   if (name==null || "".equals(name)) { 
     errorMessage = "������� ���!";
   }
   else{ 
     errorMessage = processLogonAttempt(name, request, response); 
   } 
   
   
   if (errorMessage!=null) {  
     request.getSession().setAttribute("name", null); 
     request.getSession().setAttribute("error", errorMessage); 
     response.sendRedirect(response.encodeRedirectURL("/Laba_8/"));
   } 
 } 
  

 
String processLogonAttempt(String name, HttpServletRequest request, HttpServletResponse response) 
		throws IOException { 
	
   String sessionId = request.getSession().getId(); 
   ChatUser aUser = activeUsers.get(name); 
  
   if (aUser==null) { 
	      aUser = new ChatUser(name, sessionId); 
	       synchronized (activeUsers) { 
	    	  activeUsers.put(aUser.getName(), aUser);    
	   
	      } 
	    }

   
   if (aUser.getSessionId().equals(sessionId)) { 
	   
     request.getSession().setAttribute("name", name);
     Cookie sessionIdCookie = new Cookie("sessionId", sessionId); 
     sessionIdCookie.setMaxAge(60*60*24*365); 
     response.addCookie(sessionIdCookie);
     messages.add(new ChatMessage("(������������ ����� � ���) ",aUser, date.toString()));
     response.sendRedirect(response.encodeRedirectURL("/Laba_8/view.html"));
     return null; 
   	} else { 
     return " <strong> ��� - " + name + " ��� ������</strong>  " +
     		"!";
   }     
 } 
  
}