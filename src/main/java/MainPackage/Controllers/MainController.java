package MainPackage.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping
    public String hello(@RequestParam(name = "first") int num1, @RequestParam(name = "second") int num2, @RequestParam(name = "sign") String sig, Model model) {
        int max = 0;
        if (sig.equals("+")) {
            max = num1 + num2;
        } else if (sig.equals("-")) {
            max = num2 - num1;
        } else if (sig.equals("/")) {
            max = num2 / num1;
        } else if (sig.equals("*")) {
            max = num1 * num2;
        }
        model.addAttribute("max", max);
        return "hello";
    }

    @GetMapping("/random")
    public String newString(@RequestParam(name = "stringLenght") int wordLenght, Model model) {
        char[] letters = new char[wordLenght];
        for (int i = 0; i < wordLenght; i++) {
            letters[i] = (char) (Math.random() * 65000);
        }
        String word = String.valueOf(letters);
        model.addAttribute("word", word);
        return "lenght";
    }
}