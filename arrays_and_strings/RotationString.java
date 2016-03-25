package cc150.arrays_and_strings;

/**
 * Created by xiaopengliu on 26/03/16.
 */
public class RotationString {

    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(isRotated(s1, s2));
    }

    public static boolean isRotated(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }
        return isSubString(s1, s2+s2);

    }

    private static boolean isSubString(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        for(int i = 0;i < len2-len1+1;i++) {
            String temp = s2.substring(i, i + len1);
            if(temp.equals(s1)) {
                return true;
            }
        }
        return false;
    }
}
