import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterN_Days957 {
    static public int[] prisonAfterNDays(int[] cells, int N) {
        int[] newCells = new int[8];
        Map<Character, Integer> cellsDate = new HashMap<>();
        Map<Integer, Character> dateCells = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            cellsDate.put(hashCells(cells), i - 1);
            dateCells.put(i - 1, hashCells(cells));
            for (int j = 1; j < 7; j++) {
                if (cells[j - 1] == cells[j + 1]) {
                    newCells[j] = 1;
                }
            }
            cells = newCells;
            newCells = new int[8];

            if (cellsDate.containsKey(hashCells(cells))) {
                int loopStart = cellsDate.get(hashCells(cells));
                int loopSize = i - loopStart;
                int daysInLoop = (N - loopStart) % loopSize;
                int date = loopStart + daysInLoop;
                return decodeCells(dateCells.get(date));
            }
        }
        return cells;
    }
    static char hashCells(int[] cells) {
        char hash = 0;
        for (int cell: cells) {
            hash <<= 1;
            hash |= cell;
        }
        return hash;
    }
    static int[] decodeCells(char hash) {
        int[] cells = new int[8];
        for (int i = 0; i < 8; i++) {
            cells[i] = (hash >> (7 - i)) & 1;
        }
        return cells;
    }
    public static void main(String[] args) {
        int[] cells = {1,0,0,1,0,0,1,0};
        System.out.println(Arrays.toString(prisonAfterNDays(cells, 1000000000)));
    }
}
