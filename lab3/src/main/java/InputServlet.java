import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Input", urlPatterns = {"/input"})
public class InputServlet extends HttpServlet {
    private List<String> languages;

    @Override
    public void init() {
        this.languages = new ArrayList<>();
        this.languages.add("English");
        this.languages.add("Romanian");
        this.languages.add("German");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("languages", languages);
        getServletContext().getRequestDispatcher("/input.jsp").forward(request, response);
    }
}
