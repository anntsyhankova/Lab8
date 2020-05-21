package servlets;

import entity.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chat/messages")
public class MessageListServlet extends ChatServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter printWriter = resp.getWriter();
		
		printWriter.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='5'></head>");
		printWriter.println("<body>");

		for (int i = Messages.size() - 1; i >= 0; i--)
		{
			Message message = Messages.get(i);
			if (message.getWhere().equalsIgnoreCase("All"))
			{
				printWriter.println("<div><strong>" + message.getAuthor() + "</strong>: " + message.getMessage() + "</div>");
			}
			else if (ActiveUsers.get(message.getWhere()).getSessionId().equals(req.getSession().getId()))
			{
				printWriter.println("<div><strong>" + message.getAuthor() + " to you</strong>: " + message.getMessage() + "</div>");
			}
			else if(ActiveUsers.get(message.getAuthor()).getSessionId().equals(req.getSession().getId()))
			{
				printWriter.println("<div><strong>you to " + message.getWhere() + "</strong>: " + message.getMessage() + "</div>");
			}
		}
		printWriter.println("</body></html>");
		
	}
	
}
