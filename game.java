/**
 * Created by Shy on 2014/9/23.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int[] nums = new int[4];

    public String toString() {
        StringBuffer result = new StringBuffer("");
        for (int i : nums) {
            result.append(i + " ");
        }
        return result.toString();
    }

    public Game() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int k = r.nextInt(10 - i);
            nums[i] = list.get(k);
            list.remove(k);
        }
    }

    public boolean validate(String r) {
        if (r.length() != 4) {
            return false;
        }
        if (!r.matches("\\d{4}")) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (r.lastIndexOf(r.charAt(i)) > i) {
                return false;
            }
        }
        return true;
    }

    private String check1(String r) {
        int count = 0;
        for (int i = 0; i < r.length(); i++) {
            if (nums[i] == Integer.parseInt(r.charAt(i) + "")) {
                count++;
            }
        }
        return count + "A";
    }

    private String check2(String r) {
        int count = 0;
        for (int i = 0; i < r.length(); i++) {
            int temp = Integer.parseInt(r.charAt(i) + "");
            for (int j = 0; j < r.length(); j++) {
                if (nums[j] == temp && i != j)
                    count++;
            }
        }
        return count + "B";
    }

    public String check(String r) {
        return check1(r) + check2(r);
    }

    public static void main(String[] args) {
        Game g = new Game();
        System.out.println(g);
        Scanner s = new Scanner(System.in);
        while (true) {
            String r = s.next();
            if ("exit".equals(r))
                break;
            if (!g.validate(r)) {
                System.out.println("输入4位数字, 且不能重复");
                continue;
            }
            String result = g.check(r);
            if ("4A0B".equals(result)) {
                System.out.println("恭喜, 猜对了");
                break;
            } else {
                System.out.println(result);
            }
        }
    }

}
