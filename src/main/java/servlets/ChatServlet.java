package servlets;

import entity.Message;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/start")
public class ChatServlet extends HttpServlet
{
	protected HashMap<String, User> ActiveUsers;
	protected ArrayList<Message> Messages;
	
	//Messages.add(new ChatMessage("system", "", 123, "all"));
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		
		ActiveUsers = (HashMap<String, User>) getServletContext().getAttribute("ActiveUsers");
		Messages = (ArrayList<Message>) getServletContext().getAttribute("Messages");
		
		if(ActiveUsers == null)
		{
			ActiveUsers = new HashMap<String, User>();
			getServletContext().setAttribute("ActiveUsers", ActiveUsers);
		}
		
		if(Messages == null)
		{
			Messages = new ArrayList<Message>();
			getServletContext().setAttribute("Messages", Messages);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.sendRedirect("/chat/login");
	}
}
