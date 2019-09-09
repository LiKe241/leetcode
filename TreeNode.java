import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    // outputs result of level order traversal including null leaves
    private ArrayList<Integer> toList() {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> toVisit = new LinkedList<>();

        toVisit.add(this);
        while (!toVisit.isEmpty()) {
            TreeNode ptr = toVisit.poll();
            if (ptr == null) {
                result.add(null);
            } else {
                result.add(ptr.val);
                toVisit.add(ptr.left);
                toVisit.add(ptr.right);
            }
        }

        return result;
    }


    @Override
    public String toString() {
        // trims null leaves at the end of toList()
        ArrayList<Integer> res = toList();
        int lastNum = 0;
        for (int i = 0; i < res.size(); ++i) if (res.get(i) != null) lastNum = i;
        return res.subList(0, lastNum + 1).toString();
    }

    // constructs the tree assuming nodeValues are result of level order traversal including null leaves
    static TreeNode construct(ArrayList<Integer> nodeValues) {
        if (nodeValues == null || nodeValues.size() == 0) return null;

        TreeNode root = new TreeNode(nodeValues.get(0));
        int index = 1;
        Queue<TreeNode> toConstruct = new LinkedList<>();
        toConstruct.add(root);

        while (!toConstruct.isEmpty()) {
            TreeNode curr = toConstruct.poll();
            if (index < nodeValues.size() && nodeValues.get(index) != null) {
                curr.left = new TreeNode(nodeValues.get(index));
                toConstruct.add(curr.left);
            }
            if (index + 1 < nodeValues.size() && nodeValues.get(index + 1) != null) {
                curr.right = new TreeNode(nodeValues.get(index + 1));
                toConstruct.add(curr.right);
            }
            index += 2;
        }

        return root;
    }
}
