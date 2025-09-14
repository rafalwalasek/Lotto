package pl.rafal.lotto.model;

import jakarta.persistence.*;

@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private int count;

    @OneToOne
    @JoinColumn(name = "player_id", unique = true)
    private Player player;

    public Balance() {}
    public Balance(int amount, int count) {
        this.amount = amount;
        this.count = count;
    }

    public Long getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public int getCount() {
        return count;
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
    public void setCount(int count) {
        this.count = count;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
