import java.util.*;
public class Solution {
    public Solution() {

    }

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }

    public String calculator(String info) {
        String[] variables = info.split("~");
        double total = Double.valueOf(variables[0]);
        double month_ins = Double.valueOf(variables[1]) / 1200;
        double time =  12 * Double.valueOf(variables[2]);
        double downplay = Double.valueOf(variables[3]);
        double loan = total - downplay;
        double result = (month_ins * loan) / (1 - Math.pow(1 + month_ins, -time));
        return String.format("%.2f", result);
    }

    public String stock(String info) {
        String[] invest = info.split("\\|");
        String port = invest[0];
        String bench = invest[1];
        //port
        String[] info1 = port.split(":");
        String[] stock1 = info1[1].split(";");
        Map<String, Double> map1 = new LinkedHashMap<>();
        double total1 = 0.0;
        for (String s : stock1) {
            String[] temp = s.split(",");
            total1 += Integer.valueOf(temp[1]) * Double.valueOf(temp[2]);
        }
        for (String s : stock1) {
            String[] temp = s.split(",");
            map1.put(temp[0], (Integer.valueOf(temp[1]) * Double.valueOf(temp[2])) / total1);
        }
        //Bench
        String[] info2 = bench.split(":");
        String[] stock2 = info2[1].split(";");
        Map<String, Double> map2 = new LinkedHashMap<>();
        double total2 = 0.0;
        for (String s : stock2) {
            String[] temp = s.split(",");
            total2 += Integer.valueOf(temp[1]) * Double.valueOf(temp[2]);
        }
        for (String s : stock2) {
            String[] temp = s.split(",");
            map2.put(temp[0], (Integer.valueOf(temp[1]) * Double.valueOf(temp[2])) / total2);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            sb.append(pair.getKey());
            sb.append(":");
            if (map2.containsKey(pair.getKey())) {
                double result = ((double)pair.getValue() - map2.get(pair.getKey())) * 100;
                sb.append(String.format("%.2f", result));
                map2.remove(pair.getKey());
            } else {
                double result = (double)pair.getValue() * 100;
                sb.append(String.format("%.2f", result));
            }
            sb.append(",");
            it.remove();
        }
        if (map2.size() == 0) {
            return sb.deleteCharAt(sb.capacity() - 1).toString();
        }
        Iterator iti = map2.entrySet().iterator();
        while (iti.hasNext()) {
            Map.Entry pair = (Map.Entry)iti.next();
            sb.append(pair.getKey());
            sb.append(":");
            double result = (0 - (double)pair.getValue()) * 100;
            sb.append(String.format("%.2f", result));
            sb.append(",");
            iti.remove();
        }
        String output = sb.toString();
        //delete the last comma
        return output.substring(0, output.length() - 1);
    }

    public static int[] plusOne(int[] digits) {
        int carry  = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == 0) {
                carry = (digits[i] + 1) / 10;
                digits[i] = (digits[i] + 1) % 10;
                continue;
            }
            carry = (digits[i] + carry) / 10;
            digits[i] = (digits[i] + carry) % 10;
        }

        if (carry == 0) {
            return digits;
        }
        int[] result = new int[digits.length + 1];
        for (int i = 1; i < result.length; i++) {
            result[i] = digits[i - 1];
        }
        result[0] = 1;
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode temp = queue.poll();
                level.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            result.add(level);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] a = new int[]{9};
        Solution p = new Solution();
//        System.out.println(p.maxSubArray(a))
        TreeNode root = new TreeNode(3);
        TreeNode r1 = new TreeNode(9);
        TreeNode r2 = new TreeNode(20);
        TreeNode r3 = new TreeNode(15);
        TreeNode r4 = new TreeNode(7);
        root.left = r1;
        root.right = r2;
        r2.left = r3;
        r2.right = r4;
        System.out.println(p.levelOrder(root));
        // System.out.println(p.stock("PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20"));
        //String line = "-10, 2, 3, -2, 0, 5, -15";
//        String[] array =  line.split(",\\s*");
//        for (String s : array) {
//            System.out.print(Integer.valueOf(s));
//        }
//        System.out.println(Integer.valueOf("-10"));
    }
}