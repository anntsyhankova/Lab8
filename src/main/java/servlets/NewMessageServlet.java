package servlets;

import entity.Message;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebServlet("/chat/send_message")
public class NewMessageServlet extends ChatServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		if (req.getParameter("message").equals("") ||
				(!ActiveUsers.containsKey(req.getParameter("person")) && !req.getParameter("person").equalsIgnoreCase("all")) ||
				req.getParameter("person").equals(req.getSession().getAttribute("username")))
		{
			resp.sendRedirect("/web/views/new_message.html");
			return;
		}
		
		Message message = new Message((String)req.getSession().getAttribute("username"), req.getParameter("message"), Calendar.getInstance().getTimeInMillis(), req.getParameter("person"));
		Messages.add(message);
		
		for(User user: ActiveUsers.values())
		{
			if (user.getName().equals(req.getSession().getAttribute("username")))
			{
				user.setLastInteractionTime(Calendar.getInstance().getTimeInMillis());
				break;
			}
		}
		
		resp.sendRedirect("/views/new_message.html");
	}
}
