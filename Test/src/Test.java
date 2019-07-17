

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test.do")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");   // Set content type of the response so that jQuery knows what it can expect.
		response.setCharacterEncoding("UTF-8");
		String retStr = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/docker", "root", "root1234");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from user");
			while (rs.next()) {				
				retStr = rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "\n";
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		response.getWriter().write(new Gson().toJson(retStr));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
