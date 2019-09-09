import java.util.ArrayList;
import java.util.List;

public class Unique_BST_II_95 {
    private static List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = recursiveGenerate(1, n);
        return result;
    }

    private static List<TreeNode> recursiveGenerate(int low, int high) {
        List<TreeNode> result = new ArrayList<>();
        if (low > high) {
            result.add(null);
            return result;
        }
        if (low == high) {
            result.add(new TreeNode(low));
            return result;
        }

        for (int currVal = low; currVal <= high; ++currVal) {
            TreeNode currNode = new TreeNode(currVal);
            List<TreeNode> leftList = recursiveGenerate(low, currVal - 1);
            List<TreeNode> rightList = recursiveGenerate(currVal + 1, high);
            for (TreeNode leftHead: leftList) {
                for (TreeNode rightHead: rightList) {
                    currNode.left = leftHead;
                    currNode.right = rightHead;
                    result.add(deepCopy(currNode));
                }
            }
        }

        return result;
    }

    private static TreeNode deepCopy(TreeNode node) {
        TreeNode newNode = new TreeNode(node.val);
        if (node.left != null) {
            newNode.left = deepCopy(node.left);
        }
        if (node.right != null) {
            newNode.right = deepCopy(node.right);
        }
        return newNode;
    }

    public static void main (String[] args) {
        List<TreeNode> result = generateTrees(3);
        System.out.println(result);
    }
}
