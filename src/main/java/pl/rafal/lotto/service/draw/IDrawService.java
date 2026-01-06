package pl.rafal.lotto.service.draw;

import pl.rafal.lotto.model.draw.Draw;

public interface IDrawService {
    Draw performDraw();
    Draw getCurrentDraw();
}
