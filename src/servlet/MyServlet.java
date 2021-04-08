package servlet;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

import com.tuananh.ICSExporter;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/getFile")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String studentCode = request.getParameter("code");
		String token = request.getParameter("downloadToken");
		
		ICSExporter exporter = new ICSExporter(studentCode, token);

		
		boolean result = false;
		try {

			result = exporter.export(response);
			if(result == false) {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				OutputStream out = response.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(out);
				osw.write("Mã sinh viên không đúng");
				osw.close();
				out.close();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String studentCode = request.getParameter("code");
		String token = request.getParameter("downloadToken");
		
		ICSExporter exporter = new ICSExporter(studentCode, token);

		
		boolean result = false;
		try {

			result = exporter.export(response);
			if(result == false) {
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				OutputStream out = response.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(out);
				osw.write("Mã sinh viên không đúng");
				osw.close();
				out.close();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
