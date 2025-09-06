package pl.rafal.lotto.model;

import jakarta.persistence.*;

@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;

    @OneToOne
    @JoinColumn(name = "player_id", unique = true)
    private Player player;

    public Balance() {}

    public Long getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public Player getPlayer() {
        return player;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
