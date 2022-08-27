package com.bharath.gradle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CouponServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private CouponServlet couponServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doGet() throws ServletException, IOException {
        // Given
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        // When
        when(response.getWriter()).thenReturn(printWriter);
        couponServlet.doGet(request, response);
        // Then
        assertEquals("SUPERSALE", stringWriter.toString());
    }

    @Test
    void doPost() throws ServletException, IOException {
        // Given
        String coupon = "SUPERSALE";
        // When
        when(request.getParameter("coupon")).thenReturn(coupon);
        when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
        couponServlet.doPost(request, response);
        // Then
        verify(request).setAttribute("discount", "Discount for coupon " + coupon + " is 50%");
        verify(requestDispatcher).forward(request, response);
    }
}