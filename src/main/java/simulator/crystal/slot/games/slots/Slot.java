package simulator.crystal.slot.games.slots;

public interface Slot {

    SlotResult calculatePaylines(long bet);

	String getName();
}
