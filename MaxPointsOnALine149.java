import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxPointsOnALine149 {
    static class Line {
        float slope;
        float yIntercept;
        Line(float s, float yInt) {
            slope = s;
            yIntercept = yInt;
        }
        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }
        @Override
        public boolean equals(Object otherLine) {
            return (this.toString().equals(otherLine.toString()));
        }
        @Override
        public String toString() {
            return String.valueOf(slope) + "," + String.valueOf(yIntercept);
        }
    }
    public static int maxPoints(int[][] points) {
        if (points.length == 0) return 0;
        Map<Line, Set<Integer>> lineToPoints = new HashMap<>();
        for (int i = 0; i < points.length - 1; ++i) {
            int[] p1 = points[i];
            for (int j = i + 1; j < points.length; ++j) {
                int[] p2 = points[j];
                Line line = getLine(p1, p2);
                Set<Integer> pointsOnLine = new HashSet<>();
                if (lineToPoints.containsKey(line)) {
                    pointsOnLine = lineToPoints.get(line);
                    System.out.println(pointsOnLine);
                }
                pointsOnLine.add(i);
                pointsOnLine.add(j);
                lineToPoints.put(line, pointsOnLine);
            }
        }
        int maxNum = 1;
        for (Set<Integer> pointsOnLine: lineToPoints.values()) {
            maxNum = Math.max(maxNum, pointsOnLine.size());
        }
        return maxNum;
    }
    private static Line getLine(int[] p1, int[] p2) {
        float slope = (float) (p1[1] - p2[1]) / (p1[0] - p2[0]);
        if (Float.isInfinite(slope)) {
            return new Line(Float.POSITIVE_INFINITY, p1[0]);
        }
        float yIntercept = p1[1] - slope * p1[0];
        return new Line(slope, yIntercept);
    }

    public static void main(String[] args) {
        int[][] points = {{0,-12},{5,2},{2,5},{0,-5},{1,5},{2,-2},{5,-4},{3,4},{-2,4},{-1,4},{0,5},{0,8},{-2,-1},{0,-11},{0,-9}};
        System.out.println(maxPoints(points));
    }
}
