import java.util.ArrayList;
import java.util.List;

public class InsertInterval57 {
    private static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resultList = new ArrayList<>();
        int i = 0;
        // adds intervals before newInterval
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            resultList.add(intervals[i++]);
        }
        // merges intervals covered by newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        resultList.add(newInterval);
        // add rest intervals
        while (i < intervals.length) {
            resultList.add(intervals[i++]);
        }
        // converts List to int[][]
        int[][] resultArr = new int[resultList.size()][2];
        i = 0;
        for (int[] interval: resultList) {
            resultArr[i++] = interval;
        }
        return resultArr;
    }
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        insert(intervals, newInterval);
    }
}
