package simulator.crystal.slot.games;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


public class ThreeDrumSlot implements Slot{
    final static int[] drumLine = new int[100];


    private void initialiseDrumLine() {
        for(int i = 0; i < 70; i++) {
            drumLine[i] = 1;
        }
        for(int i =70; i < 95; i++) {
            drumLine[i] = 2;
        }
        for(int i = 95; i < 100; i++) {
            drumLine[i] = 7;
        }
    }

    @Override
    public Long calculatePaylines (long bet) {
        SecureRandom secureRandom = new SecureRandom();
        int[] result = new int[3];
        result[0] = drumLine[secureRandom.nextInt(drumLine.length)];
        result[1] = drumLine[secureRandom.nextInt(drumLine.length)];
        result[2] = drumLine[secureRandom.nextInt(drumLine.length)];

        Map<Integer, Integer> map = new HashMap<>();
        for(int x : result) {
            map.merge(x, 1, Integer::sum);
        }
        if(map.getOrDefault(7, 0) == 3) return bet*50;
        if(map.getOrDefault(7, 0) == 2) return bet*5;

        if(map.getOrDefault(2, 0) == 3) return bet*10;
        if(map.getOrDefault(2, 0) == 2) return (long) (bet * 1.5);

        if(map.getOrDefault(1, 0) == 3) return (long) (bet * 1.2);
        if(map.getOrDefault(1, 0) == 2) return (long) (bet * 0.4);

        return 0L;
    }
}
