package simulator.crystal.slot.games;

public interface Game {
    SpinResult play(Long userId, Long bet);
}
