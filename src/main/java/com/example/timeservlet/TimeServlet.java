package com.example.timeservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezone = req.getParameter("timezone");
        ZoneId zoneId;

        if (timezone == null || timezone.isEmpty()) {
            zoneId = ZoneId.of("UTC");
        } else {
            zoneId = ZoneId.of(timezone);
        }

        LocalDateTime currentTime = LocalDateTime.now(zoneId);
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head><title>Current Time</title></head>");
        writer.println("<body>");
        writer.println("<h1>Current Time</h1>");
        writer.println("<p>" + formattedTime + " " + zoneId + "</p>");
        writer.println("</body>");
        writer.println("</html>");
    }
}


