import Beans.WordBean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Result", urlPatterns = {"/result"},
            initParams = { @WebInitParam(name = "language", value = "English")})
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<WordBean> words = this.readWords();
        request.setAttribute("words", words);
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        String word = request.getParameter("word");
        String definition = request.getParameter("definition");

        if (language == null || language.isEmpty()) {
            language = this.getInitParameter("language");
//            request.setAttribute("errorMessage", "Language is missing");
//            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
//            return;
        }
        if (word == null || word.isEmpty()) {
            request.setAttribute("errorMessage", "Word is missing");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        if (definition == null || definition.isEmpty()) {
            request.setAttribute("errorMessage", "Definition is missing");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        WordBean wordBean = new WordBean(language, word, definition);
        this.addWord(wordBean);

        List<WordBean> words = this.readWords();
        request.setAttribute("words", words);
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }

    private List<WordBean> readWords() throws IOException {
        List<WordBean> words = new ArrayList();
        String filename = "/WEB-INF/words.csv";
        ServletContext context = getServletContext();

        InputStream is = context.getResourceAsStream(filename);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] separatedLine = line.split(",");
                words.add(new WordBean(separatedLine[0], separatedLine[1], separatedLine[2]));
            }
        }
        return words;
    }

    private void addWord(WordBean word) throws IOException {
        String filename = "/WEB-INF/words.csv";
        ServletContext context = getServletContext();

        FileWriter writer = new FileWriter(context.getRealPath(filename), true);
        String stringWord = word.getLanguage() + "," + word.getWord() + "," + word.getDescription() + "\n";
        writer.write(stringWord);
        writer.close();
    }
}
