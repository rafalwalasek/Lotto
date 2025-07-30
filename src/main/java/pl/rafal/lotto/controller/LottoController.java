package pl.rafal.lotto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.repository.PlayerRepository;

@Controller
public class LottoController {
    private final PlayerRepository playerRepository;

    public LottoController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/save-to-db")
    public String saveToDB(@ModelAttribute Player player, Model model) {
        if (player.getAge() < 18) {
            model.addAttribute("error", "Musisz mieć conajmniej 18 lat aby dołączyć do gry!");
            return "index";
        }

        playerRepository.save(player);
        return "save-to-db";
    }
}
