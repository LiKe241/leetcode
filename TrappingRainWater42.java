import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TrappingRainWater42 {
    private static int trap(int[] height) {
        Stack<Integer> monoDecIndex = new Stack<>();
        int volWater = 0;
        for (int i = 0; i < height.length; ++i) {
            if ((monoDecIndex.size() == 0 || height[i] < height[monoDecIndex.peek()]) && height[i] > 0) {
                if (monoDecIndex.size() > 0)
                    volWater += height[i] * (i - monoDecIndex.peek() - 1);
                monoDecIndex.push(i);
            } else if (height[i] > 0) {
                List<Integer> poppedIndex = new ArrayList<>();
                while (monoDecIndex.size() > 0 && height[i] >= height[monoDecIndex.peek()]) {
                    poppedIndex.add(monoDecIndex.pop());
                }
                int prevHeight = 0;
                for (Integer currIndex: poppedIndex) {
                    int currHeight = height[currIndex];
                    volWater += (currHeight - prevHeight) * (i - currIndex - 1);
                    prevHeight = currHeight;
                }
                if (monoDecIndex.size() > 0) {
                    volWater += (height[i] - prevHeight) * (i - monoDecIndex.peek() - 1);
                }
                monoDecIndex.push(i);
            }
        }
        return volWater;
    }
    public static void main(String[] args) {
        int[] height = {3,0,1,2};
        System.out.println(trap(height));
    }
}
/*

 */