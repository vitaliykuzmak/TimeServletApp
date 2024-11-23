package com.example.timeservlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timezone = request.getParameter("timezone");
        if (timezone != null && !isValidTimezone(timezone)) {
            response.setContentType("text/html");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter writer = response.getWriter();
            writer.println("<html>");
            writer.println("<head><title>Invalid Timezone</title></head>");
            writer.println("<body>");
            writer.println("<h1>Invalid timezone</h1>");
            writer.println("</body>");
            writer.println("</html>");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isValidTimezone(String timezone) {
        return TimeZone.getAvailableIDs().length > 0 && TimeZone.getAvailableIDs().equals(timezone);
    }
}

