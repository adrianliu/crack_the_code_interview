package cc150.arrays_and_strings;

/**
 * Created by xiaopengliu on 26/03/16.
 */
public class Anagram {

    public static void main(String[] args) {
        new Anagram();
    }

    public Anagram() {
        String s1 = "aabbcc";
        String s2 = "cbaabc";
        System.out.println(isAnagram(s1, s2));
    }

    private boolean isAnagram(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        int[] hit = new int[256];
        for(int i = 0;i < s1.length();i++) {
            char ch = s1.charAt(i);
            hit[ch]++;
        }
        for(int j = 0;j < s2.length();j++) {
            char ch = s2.charAt(j);
            hit[ch]--;
        }
        for(int i = 0;i < 256;i++) {
            if(hit[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
