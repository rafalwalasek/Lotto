package pl.rafal.lotto.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.rafal.lotto.model.Balance;
import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.model.Ticket;
import pl.rafal.lotto.repository.PlayerRepository;
import pl.rafal.lotto.service.*;

import java.util.*;

@RestController
@RequestMapping("/api/bets")
@CrossOrigin(origins = "http://localhost:3000")
public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }



//    private final NumberGeneratorService numberGeneratorService;
//    private final PlayerRepository playerRepository;
//    private final PaymentService paymentService;
//    private final PlayerService playerService;
//    private final CouponService couponService;
//
//    public LottoController(NumberGeneratorService numberGeneratorService, PlayerRepository playerRepository, PaymentService paymentService,
//            PlayerService playerService, CouponService couponService) {
//        this.numberGeneratorService = numberGeneratorService;
//        this.paymentService = paymentService;
//        this.playerRepository = playerRepository;
//        this.playerService = playerService;
//        this.couponService = couponService;
//    }

//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }
//    @GetMapping("/user-panel")
//    public String userPanel(HttpSession session, Model model) {
//        Long playerId = (Long) session.getAttribute("loggedPlayerId");
//        if (playerId == null) {
//            return "redirect:/";
//        }
//
//        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Nie znaleziono gracza"));
//
//        List<Ticket> activeTickets = new ArrayList<>();
//        if (player.getTickets() != null) {
//            for (Ticket ticket : player.getTickets()) {
//                if (!ticket.isExpired()) {
//                    if (ticket.getNumbers() == null) {
//                        ticket.setNumbers(new ArrayList<>());
//                    }
//                    activeTickets.add(ticket);
//                }
//            }
//        }
//
//        Set<Integer> drawNumbers = numberGeneratorService.getDrawNumbers();
//        lottoService.processDraw(player, drawNumbers);
//
//        Balance balance = player.getBalance();
//        if (balance == null) {
//            balance = new Balance();
//            balance.setAmount(0);
//            balance.setCount(0);
//        }
//
//        model.addAttribute("player", player);
//        model.addAttribute("tickets", activeTickets);
//        model.addAttribute("showNumbers", true);
//        model.addAttribute("numbers", drawNumbers);
//        model.addAttribute("message", "");
//        model.addAttribute("balance", balance);
//
//        return "user-panel";
//    }
//
//    @PostMapping("/save-to-db")
//    public String saveToDB(@ModelAttribute Player player, Model model) {
//        try {
//            if (player.getAge() < 18) {
//                model.addAttribute("registerError", "Musisz mieć conajmniej 18 lat aby dołączyć do gry!");
//                return "index";
//            }
//            if (player.getName().isBlank() || player.getEmail().isBlank() || player.getPassword().isBlank()) {
//                model.addAttribute("registerError", "Wszystkie pola są wymagane!");
//                return "index";
//            }
//
//            playerRepository.save(player);
//            return "save-to-db";
//        } catch (RuntimeException e) {
//            model.addAttribute("registerError", e.getMessage());
//            return "index";
//        }
//    }
//    @PostMapping("/login")
//    public String login(@ModelAttribute Player player, HttpSession session, Model model) {
//        try {
//            Player dbPlayer = playerService.login(player.getEmail(), player.getPassword());
//            session.setAttribute("loggedPlayerId", dbPlayer.getId());
//            return "redirect:/user-panel";
//        } catch (RuntimeException e) {
//            model.addAttribute("loginError", e.getMessage());
//            return "index";
//        }
//    }
//    @PostMapping("/deposit")
//    public String deposit(HttpSession session, @RequestParam("amount") int amount, @RequestParam("method") String method) {
//        Long playerId = (Long) session.getAttribute("loggedPlayerId");
//        if (playerId == null) {
//            return "redirect:/";
//        }
//
//        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Nie znaleziono gracza"));
//
//        paymentService.deposit(player, amount, method);
//
//        return "redirect:/user-panel";
//    }
//    @PostMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/index";
//    }
//    @PostMapping("/buyCoupon")
//    public String buyCoupon(HttpSession session, @RequestParam("amount") int amount, @RequestParam("numbers") String numbers) {
//        Long playerId = (Long) session.getAttribute("loggedPlayerId");
//        if (playerId == null) {
//            return "redirect:/";
//        }
//
//        String[] chosen = numbers.split(",");
//        List<Integer> chosenNumbers = new ArrayList<>();
//        for (String s : chosen) {
//            chosenNumbers.add(Integer.parseInt(s.trim()));
//        }
//
//        try {
//            couponService.buyCoupon(playerId, amount, chosenNumbers);
//        } catch (RuntimeException e) {
//            return "redirect:/user-panel";
//        }
//
//        return "redirect:/user-panel";
//    }
}
