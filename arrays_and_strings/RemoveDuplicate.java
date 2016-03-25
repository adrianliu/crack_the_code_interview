package cc150.arrays_and_strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by xiaopengliu on 25/03/16.
 */
public class RemoveDuplicate {

    public static void main(String[] args) {

        new RemoveDuplicate();
    }

    public RemoveDuplicate() {
        String str = "abbhjhhsjjj";
        System.out.println("Method 1: " + removeDupChars1(str));
        System.out.println("Method 2: " + removeDupChars2(str));
        System.out.println("Method 3: " + removeDupChars3(str));
        System.out.println("Method 4: " + removeDupChars4(str));

    }
    //O(nlogn) -- sorting changed the relative order of characters.
    private String removeDupChars1(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        Arrays.sort(arr); //use quick sort which does not need extra space. O(nlogn)
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = i + 1;
            while(j < n) {
                if(arr[j] == arr[i]) {
                    //remove jth character
                    arr[j] = '0';
                    j++;
                } else {
                    i = j;
                    break;
                }
            }
            if(j == n) {
                break;
            }

        }
        int m = 0;
        for(int k = 0;k < n;k++) {
            if(arr[k] != '0') {
                arr[m++] = arr[k];
            }
        }

        return new String(arr, 0, m);
    }

    //O(n^2) --- not changing the original relative order of characters.
    private String removeDupChars2(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        int n = arr.length;
        int tail = 0;
        for(int i = 0;i < n;i++) {
            int j = 0;
            while(j <= tail) {
                if(arr[j] == arr[i]) {
                    break;
                }
                j++;
            }
            if(j == tail + 1) {
                arr[++tail] = arr[i];
            }
        }
        for(int k = tail + 1;k < n;k++) {
            arr[k] = '\0';
        }

        return new String(arr);

    }

    //O(n) --- Using extra space HashSet. Relative Order might change also.
    private String removeDupChars3(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        HashSet<Character> set = new HashSet<>();
        for(char ch: arr) {
            set.add(ch);
        }
        int k = 0;
        Iterator<Character> iter = set.iterator();
        while(iter.hasNext()) {
            arr[k++] = iter.next();
        }
        return new String(arr, 0, k);

    }
    //O(n) --- Using extra space array(constant size).
    private String removeDupChars4(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        int[] hit = new int[256]; //there are 256 ASCII characters in total.
        int k = 0;
        for(char ch: arr) {
            if(hit[ch] == 0) { //if ch is new char
                arr[k++] = ch;
                hit[ch] = 1;
            }
        }

        return new String(arr, 0, k);


    }





}
