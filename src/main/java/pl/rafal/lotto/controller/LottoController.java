package pl.rafal.lotto.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.rafal.lotto.model.Balance;
import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.model.Ticket;
import pl.rafal.lotto.repository.PlayerRepository;
import pl.rafal.lotto.service.NumberGeneratorService;
import pl.rafal.lotto.service.PaymentService;

import java.time.LocalTime;
import java.util.*;

@Controller
public class LottoController {
    private final PlayerRepository playerRepository;
    private final PaymentService paymentService;
    private final NumberGeneratorService numberGeneratorService;

    public LottoController(PlayerRepository playerRepository, PaymentService paymentService, NumberGeneratorService numberGeneratorService) {
        this.playerRepository = playerRepository;
        this.paymentService = paymentService;
        this.numberGeneratorService = numberGeneratorService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/save-to-db")
    public String saveToDB(@ModelAttribute Player player, Model model) {
        if (player.getAge() < 18) {
            model.addAttribute("registerError", "Musisz mieć conajmniej 18 lat aby dołączyć do gry!");
            return "index";
        } else if (player.getName().isBlank() || player.getEmail().isBlank() || player.getPassword().isBlank()) {
            model.addAttribute("registerError", "Wszystkie pola są wymagane!");
            return "index";
        }

        if (playerRepository.existsByEmail(player.getEmail())) {
            model.addAttribute("registerError", "Użytkownik z takim e-mailem już istnieje.");
            return "index";
        }

        Balance balance = new Balance(0, 0);
        balance.setPlayer(player);
        player.setBalance(balance);

        playerRepository.save(player);
        return "save-to-db";
    }

    @GetMapping("/user-panel")
    public String userPanel(HttpSession session, Model model) {
        Player player = (Player) session.getAttribute("loggedPlayer");

        if (player == null) {
            return "redirect:/";
        }

        model.addAttribute("player", player);

        if (player.getBalance() != null) {
            model.addAttribute("balance", player.getBalance());
        } else {
            Balance balance = new Balance();
            balance.setAmount(0);
            model.addAttribute("balance", balance);
        }

        model.addAttribute("tickets", player.getTickets());

        LocalTime now = LocalTime.now();
        LocalTime drawTime = LocalTime.of(12, 40);

        if (now.isBefore(drawTime)) {
            model.addAttribute("showNumbers" , false);
            model.addAttribute("message", "Wyliki losowania o 20:00");
        } else {
            model.addAttribute("showNumbers" , true);
            Set<Integer> numbers = numberGeneratorService.generateLottoNumbers();
            model.addAttribute("numbers", numbers);
        }

        return "user-panel";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Player player, HttpSession session, Model model) {
        Player dbPlayer = playerRepository.findByEmail(player.getEmail());

        if (dbPlayer == null || !dbPlayer.getPassword().equals(player.getPassword())) {
            model.addAttribute("loginError", "Nieprawidłowy email lub hasło!");
            return "index";
        }

        session.setAttribute("loggedPlayer", dbPlayer);
        return "redirect:/user-panel";
    }

    @PostMapping("/deposit")
    public String deposit(HttpSession session, @RequestParam("amount") int amount, @RequestParam("method") String method) {
        Player player = (Player) session.getAttribute("loggedPlayer");
        if (player == null) {
            return "redirect:/";
        }

        paymentService.deposit(player, amount, method);

        return "redirect:/user-panel";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @PostMapping("/buyCoupon")
    public String buyCoupon(HttpSession session, @RequestParam("amount") int amount, @RequestParam("numbers") String numbers) {
        Player player = (Player) session.getAttribute("loggedPlayer");
        if (player == null) {
            return "redirect:/";
        }

        String[] chosen = numbers.split(",");
        if (chosen.length != 6) {
            return "redirect:/user-panel";
        }

        Balance balance = player.getBalance();
        if (balance == null || balance.getAmount() < amount || balance.getCount() >= 2) {
            return "redirect:/user-panel";
        }

        List<Integer> chosenNumbers = new ArrayList<>();
        for (String s : chosen) {
            chosenNumbers.add(Integer.parseInt(s));
        }

        Ticket ticket = new Ticket();
        ticket.setNumbers(chosenNumbers);
        ticket.setPlayer(player);

        player.getTickets().add(ticket);

        balance.setCount(balance.getCount() + 1);
        balance.setAmount(balance.getAmount() - amount);

        playerRepository.save(player);

        return "redirect:/user-panel";
    }
}
