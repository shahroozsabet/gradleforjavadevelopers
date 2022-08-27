package io.github.shahroozsabet;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Shahrooz on 8/27/2022.
 */
class HelloServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private HelloServlet helloServlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doPost() throws ServletException, IOException {
        // Given
        String username = "Shahrooz";
        // When
        when(request.getParameter("username")).thenReturn(username);
        when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
        helloServlet.doPost(request, response);
        // Then
        verify(request).setAttribute("username", "Hello " + username);
        verify(requestDispatcher).forward(request, response);
    }
}