package myProject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ItemsServlet extends HttpServlet {
    private static final String URL = "jdbc:mysql://localhost:3306/warehouse_db";
    private static final String USER = "myWMS";
    private static final String PASSWORD = "myWMS";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String categoryIdParam = request.getParameter("categoryId");
        if (categoryIdParam == null) {
            response.getWriter().println("<h3>Не указан параметр categoryId</h3>");
            return;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(categoryIdParam);
        } catch (NumberFormatException e) {
            response.getWriter().println("<h3>Неверный формат categoryId</h3>");
            return;
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, name, price FROM items WHERE category_id = ?")
        ) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            PrintWriter writer = response.getWriter();
            writer.println("<html><body>");
            writer.println("<h2>Список товаров категории #" + categoryId + "</h2>");
            writer.println("<ul>");

            while (resultSet.next()) {
                writer.println("<li>" + resultSet.getInt("id") + " - "
                        + resultSet.getString("name") + " ("
                        + resultSet.getDouble("price") + " руб.)</li>");
            }

            writer.println("</ul>");
            writer.println("</body></html>");
        } catch (SQLException e) {
            throw new ServletException("DB error", e);
        }
    }
}