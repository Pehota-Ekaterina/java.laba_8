package Servlets;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entity.ChatMessage;
import Entity.ChatUser;

public class NewMessageServlet extends ChatServlet {
	Date date = new Date();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = (String)request.getParameter("message");
		
		if (message!=null && !"".equals(message)) {
			ChatUser author = activeUsers.get((String)
					request.getSession().getAttribute("name"));
			synchronized (messages) {
				messages.add(new ChatMessage(message, author, date.toString()));
			}
		}
		response.sendRedirect("/Laba_8/compose_message.html");
	}
}