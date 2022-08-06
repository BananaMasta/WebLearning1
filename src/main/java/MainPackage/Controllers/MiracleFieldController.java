package MainPackage.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MiracleFieldController {
    String question;
    String answer;
    String hiddenWord;

    public void takeQuest() throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("questions.txt"));
        String quest = "";
        while ((quest = reader.readLine()) != null) {
            list.add(quest);
        }
        int a = (int) (Math.random() * list.size());
        String w = list.get(a);
        String[] d = w.split("\\=");
        question = d[0];
        answer = d[1].replace(" ", "");
    }

    @GetMapping("/question")
    public String questionSout(Model model) throws IOException {
        takeQuest();
        model.addAttribute("quest", question);
        hiddenWord = "*".repeat(answer.length());
        model.addAttribute("wonder", hiddenWord);
        return "questionHTML";
    }

    @PostMapping("/hello")
    public String questionAnswer(@RequestParam(name = "word") String letter, Model model) {
        model.addAttribute("quest", question);
        char[] c = hiddenWord.toCharArray();

        ArrayList<Integer> stringIndex = new ArrayList<>();
        for (int i = 0; i < answer.length(); i++) {             // Оптимальнее было бы проходить по строке не отгаданных символов.
            if (letter.contains(String.valueOf(answer.charAt(i)))) {
                stringIndex.add(i);
            }
        }

        stringIndex.forEach(i -> c[i] = answer.charAt(i));

        if (stringIndex.isEmpty()) {
            model.addAttribute("error", "Такой буквы нету");
            model.addAttribute("wonder", hiddenWord);
        } else {
            hiddenWord = String.valueOf(c);
            model.addAttribute("wonder", hiddenWord);
            if (!hiddenWord.contains("*")) {
                model.addAttribute("wonder", "Вы правильно угадали слово: " + answer);
            }
        }
        return "questionHTML";
    }
}
