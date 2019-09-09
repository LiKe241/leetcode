import java.util.*;

public class SerializeAndDeserializeBinaryTree297 {
    // uses pre-order and in-order traversal results to store tree
    // pro: does not need to store null
    // con: does not allow duplicate values
    private static class _Codec {
        public String serialize(TreeNode root) {
            StringBuilder encoded = new StringBuilder("");
            preOrder(encoded, root);
            encoded.deleteCharAt(encoded.length() - 1);
            encoded.append(';');
            inOrder(encoded, root);
            encoded.deleteCharAt(encoded.length() - 1);
            return encoded.toString();
        }

        private void preOrder(StringBuilder encoded, TreeNode root) {
            Stack<TreeNode> toVisit = new Stack<>();
            TreeNode curr;
            toVisit.push(root);
            while (!toVisit.isEmpty()) {
                curr = toVisit.pop();
                encoded.append(curr.val);
                encoded.append(' ');
                if (curr.right != null) {
                    toVisit.push(curr.right);
                }
                if (curr.left != null) {
                    toVisit.push(curr.left);
                }
            }
        }

        private void inOrder(StringBuilder encoded, TreeNode root) {
            Stack<TreeNode> toVisit = new Stack<>();
            TreeNode curr = root;
            while (!toVisit.isEmpty() || curr != null) {
                while (curr != null) {
                    toVisit.push(curr);
                    curr = curr.left;
                }
                curr = toVisit.pop();
                encoded.append(curr.val);
                encoded.append(' ');
                curr = curr.right;
            }
        }

        public TreeNode deserialize(String data) {
            String[] traversals = data.split(";");
            String[] _preOrder = traversals[0].split(" ");
            String[] _inOrder = traversals[1].split(" ");
            int n = _preOrder.length;
            int[] preOrder = new int[n];
            int[] inOrder = new int[n];
            for (int i = 0; i < n; ++i) {
                preOrder[i] = Integer.parseInt(_preOrder[i]);
                inOrder[i] = Integer.parseInt(_inOrder[i]);
            }
            return constructTree(preOrder, 0, n - 1, inOrder, 0, n - 1);
        }

        private TreeNode constructTree(int[] preOrder, int preStart, int preEnd,
                                       int[] inOrder, int inStart, int inEnd) {
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }
            TreeNode root = new TreeNode(preOrder[preStart]);
            int rootIndex = 0;
            for (int i = inStart; i <= inEnd; ++i) {
                if (inOrder[i] == root.val) {
                    rootIndex = i;
                    break;
                }
            }
            root.left = constructTree(preOrder,
                    preStart + 1,
                    preStart + (rootIndex - inStart),
                    inOrder,
                    inStart,
                    rootIndex - 1);
            root.right = constructTree(preOrder,
                    preStart + (rootIndex - inStart) + 1,
                    preEnd,
                    inOrder,
                    rootIndex + 1,
                    inEnd);
            return root;
        }
    }

    private static class Codec {
        private final String nullChar = "n";

        String serialize(TreeNode root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            preOrderSerialize(root, sb);
            return sb.substring(0, sb.length() - 1);
        }
        private void preOrderSerialize(TreeNode node, StringBuilder sb) {
            char splitter = ',';
            if (node == null) {
                sb.append(nullChar).append(splitter);
            } else {
                sb.append(node.val).append(splitter);
                preOrderSerialize(node.left, sb);
                preOrderSerialize(node.right, sb);
            }
        }

        TreeNode deserialize(String data) {
            if (data.equals("")) return null;
            return preOrderDeserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }
        private TreeNode preOrderDeserialize(Queue<String> strVal) {
            String currVal = strVal.poll();
            TreeNode node = null;
            if (!nullChar.equals(currVal)) {
                assert currVal != null;
                node = new TreeNode(Integer.parseInt(currVal));
                node.left = preOrderDeserialize(strVal);
                node.right = preOrderDeserialize(strVal);
            }
            return node;
        }
    }
    public static void main(String[] args) {
        Codec codec = new Codec();
        ArrayList<Integer> nodeVal = new ArrayList<>(Arrays.asList(5, 2, 3, null, null, 1, 4, 6, 7));
        TreeNode root = TreeNode.construct(nodeVal);
        System.out.println(codec.deserialize(codec.serialize(root)));
    }
}
