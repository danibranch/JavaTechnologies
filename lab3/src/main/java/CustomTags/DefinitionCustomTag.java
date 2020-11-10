package CustomTags;
import Beans.WordBean;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DefinitionCustomTag extends SimpleTagSupport {
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        out.print("<span>The definition of the word " + word + "</span>");
    }

    private List<WordBean> readWords() throws IOException {
        List<WordBean> words = new ArrayList();
        String filename = "/WEB-INF/words.csv";
        JspContext context = getJspContext();

//        InputStream is = this. .getResourceAsStream(filename);
//        if (is != null) {
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader reader = new BufferedReader(isr);
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                String[] separatedLine = line.split(",");
//                words.add(new WordBean(separatedLine[0], separatedLine[1], separatedLine[2]));
//            }
//        }
        return words;
    }
}
