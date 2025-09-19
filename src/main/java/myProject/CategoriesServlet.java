package myProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class CategoriesServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/warehouse_db";
    private static final String USER = "myWMS";
    private static final String PASSWORD = "myWMS";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL Driver not found", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<html><body>");
        writer.println("<h2>Список категорий</h2>");
        writer.println("<ul>");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT id, name FROM categories")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                // ссылка на ProductsServlet с передачей categoryId
                writer.println("<li><a href='items?categoryId=" + id + "'>" + name + "</a></li>");
            }

        } catch (SQLException e) {
            throw new ServletException("DB error", e);
        }

        writer.println("</ul>");
        writer.println("</body></html>");
        writer.flush();
    }
}