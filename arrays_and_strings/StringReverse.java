package cc150.arrays_and_strings;

/**
 * Created by xiaopengliu on 25/03/16.
 */
public class StringReverse {

    public static void main(String[] args) {
        String str = "abcde";
        new StringReverse(str);
    }

    public StringReverse(String str) {
        System.out.println(reverse(str));

        System.out.println(reverse2(str));
    }

    public String reverse2(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        while(i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
        return new String(arr);
    }

    public String reverse(String str) {
        if(str == null || str.length() == 0) {
            return str;
        }
        char[] arr = str.toCharArray();
        int n = arr.length;
        for(int i = 0;i < n/2;i++) { // Note: here is n/2, not n
            swap(arr, i, n-1-i);
        }
        return new String(arr);

    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
