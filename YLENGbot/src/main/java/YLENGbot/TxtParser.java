package YLENGbot;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TxtParser {
    static ArrayList<String> allQuestions = new ArrayList<>();
    static HashMap<Integer, String> answers = new HashMap<>();

    static public void GatherTasks() {
        try(FileReader task = new FileReader("C:\\Users\\Я\\IdeaProjects\\YLENGbot\\src\\main\\task\\task.txt")) {
            int c;
            int strId = 0;
            var str = new StringBuilder();
            var answ = new StringBuilder();
            while((c = task.read()) != -1) {
                if ((char)c != '(') {
                    str.append((char) c);
                }
                else {
                    while ((c = task.read()) != ')') {
                        answ.append((char)c);
                    }
                    allQuestions.add(str.toString());
                    answers.put(strId, answ.toString());
                    strId = strId + 1;
                    str.setLength(0);
                    answ.setLength(0);
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

