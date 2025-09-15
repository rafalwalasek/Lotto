package pl.rafal.lotto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Integer> numbers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Ticket() {}

    public Long getId() {
        return id;
    }
    public List<Integer> getNumbers() {
        return numbers;
    }
    public Player getPlayer() {
        return player;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
