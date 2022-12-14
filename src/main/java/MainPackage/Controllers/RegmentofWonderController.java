package MainPackage.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegmentofWonderController {
    List<String> wordList = new ArrayList<>();
    @GetMapping("/form")
    public String getForm() {
        return "formModel";
    }

    @PostMapping("")
    public String getFormWord(@RequestParam(name = "a") String word, Model model) {
        wordList.add(word);
        model.addAttribute("wordText", wordList);
        return "formModel";
    }
}
