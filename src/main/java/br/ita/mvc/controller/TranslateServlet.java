package br.ita.mvc.controller;

import br.ita.mvc.model.Dictionary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller servlet that handles translation requests.
 * Receives word from user, uses Dictionary model to translate,
 * and forwards to JSP view for display.
 */
@WebServlet("/translate")
public class TranslateServlet extends HttpServlet {
    private Dictionary dictionary;
    
    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the dictionary model
        dictionary = new Dictionary();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Set character encoding for proper handling of special characters
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // Get the word to translate from the request
        String word = request.getParameter("word");
        
        // Translate using the model
        String translation = dictionary.translate(word);
        
        // Store data for the view
        request.setAttribute("originalWord", word);
        request.setAttribute("translation", translation);
        
        // Forward to the result view
        request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to the main page
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
