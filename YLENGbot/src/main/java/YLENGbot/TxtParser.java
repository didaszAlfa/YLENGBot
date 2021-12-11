package YLENGbot;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TxtParser {
    private static final ArrayList<String> allQuestions = new ArrayList<>(); // ИСПРАВИТЬ ТАК ЧТОБЫ НЕЛЬЗЯ БЫЛО ИЗМЕНИТЬ ИЗВНЕ
    private static final HashMap<Integer, String> answers = new HashMap<>();

    public static void GatherTasks() {
        String basePath = new File("src\\main\\resources\\task\\task.txt").getAbsolutePath();
        try(FileReader task = new FileReader(basePath)) { // Изменить путь чтобы на любом ПК работало.
            System.out.print(basePath);
            int c;
            var strId = 0;
            var str = new StringBuilder();
            while((c = task.read()) != -1) {
                if ((char)c != '(') {
                    str.append((char) c);
                }
                else {
                    allQuestions.add(str.toString());
                    str.setLength(0);
                    while ((c = task.read()) != ')') {
                        str.append((char)c);
                    }
                    answers.put(strId, str.toString());
                    strId++;
                    str.setLength(0);
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<String> getAllQuestions() {
        return allQuestions;
    }

    public static HashMap<Integer, String> getAllAnswers() {
        return answers;
    }
}

