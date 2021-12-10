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
            StringBuilder str = new StringBuilder();
            StringBuilder answ = new StringBuilder();
            var strId = 0;
            while((c = task.read()) != -1) {
                if ((char)c != '(') {
                    str.append((char) c);
                }
                else {
                    while ((c = task.read()) != ')') {
                        answ.append((char)c);
                    }
                }
                if ((char)c == ')') {
                    allQuestions.add(str.toString());
                    answers.put(strId, answ.toString());
                    strId = strId + 1;
                    str = new StringBuilder();
                    answ = new StringBuilder();
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

