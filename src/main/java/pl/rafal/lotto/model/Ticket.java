package pl.rafal.lotto.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> numbers = new ArrayList<>();
    private int hits;
    private boolean expired;

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
    public int getHits() {
        return hits;
    }
    public boolean isExpired() {
        return expired;
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
    public void setHits(int hits) {
        this.hits = hits;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
