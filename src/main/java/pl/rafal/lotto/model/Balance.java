package pl.rafal.lotto.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Balance() {}

    public Long getId() {
        return id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Player getPlayer() {
        return player;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
