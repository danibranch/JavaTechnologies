import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@WebServlet(name = "HelloWorld", urlPatterns = {"/hello"})
public class HelloWorldServlet extends HttpServlet {
    private List<String> allWords;
    private Logger logger;

    @Override
    public void init() {
        logger = Logger.getLogger(getClass().getName());
        try {
            allWords = readWordsFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // http://localhost:8081/demo1-1.0/hello
    // http://localhost:8081/demo1-1.0/hello?letters=a&letters=a&letters=j&letters=v
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Http method " + request.getMethod() + " used");
        logger.info("IP address of the client is " + request.getRemoteAddr());
        logger.info("User agent of the client is " + request.getHeader("User-Agent"));
        logger.info("User language is " + request.getLocale().toString());

        StringBuilder parameterMap = new StringBuilder();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            parameterMap.append(entry.getKey()).append(" : [");
            for (String value : entry.getValue()) {
                parameterMap.append(value).append(", ");
            }
            parameterMap.delete(parameterMap.length() - 2, parameterMap.length());
            parameterMap.append("]\n");
        }
        logger.info("Parameters of the requests are " + parameterMap.toString());

        response.setContentType("text/html");

        String[] letters = request.getParameterValues("letters");
        List<Character> letterChars = new ArrayList<>();
        if (letters != null) {
            for (String letter : letters) {
                // word instead of letter
                if (letter.length() != 1) {
                    response.sendError(400, "More than one characters in one letter");
                }

                char letterChar = letter.charAt(0);
                // non-letter character
                if ((letterChar < 'a' || letterChar > 'z') && (letterChar < 'A' || letterChar > 'Z')) {
                    response.sendError(400, "Non-letter character");
                }
                letterChars.add(Character.toLowerCase(letterChar));
            }
        }

        List<String> formableWords = findWordsForLetters(letterChars, allWords);

        String userAgent = request.getHeader("User-Agent");
        PrintWriter out = new PrintWriter(response.getWriter());

        if (userAgent.equals("Desktop app")) {
            out.println(formableWords.toString());
        } else {
            out.println("<html><head><title>Hello</title></head>");
            out.println("<body><p>Received letters:  " + letterChars.toString() + "</p>");
            out.println("<p>The words that can be formed are " + formableWords.toString() + "</p>");
            out.println("</body></html>");
        }
        out.close();
    }

    private List<String> findWordsForLetters(List<Character> letters, List<String> words) {
        List<String> goodWords = new ArrayList<>();
        for (String word : words) {
            if (isWordFormable(word, letters))
                goodWords.add(word);
        }

        return goodWords;
    }

    private boolean isWordFormable(String word, List<Character> letters) {
        List<Character> lettersToCheck = new ArrayList(letters);
        for (int i = 0; i < word.length(); i++) {
            if (!lettersToCheck.contains(Character.toLowerCase(word.charAt(i)))) {
                return false;
            }
            lettersToCheck.remove((Object) Character.toLowerCase(word.charAt(i)));
        }
        return true;
    }

    private List<String> readWordsFromFile() throws IOException {
        List<String> words = new ArrayList<>();
        String filename = "/WEB-INF/words.txt";
        ServletContext context = getServletContext();

        InputStream is = context.getResourceAsStream(filename);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String word;

            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
        }
        return words;
    }
}
