package cc150.arrays_and_strings;

/**
 * Created by xiaopengliu on 26/03/16.
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        new ReplaceSpace();
    }

    public ReplaceSpace() {
        String s = "ab c ";
        System.out.println(repleaceSpaces(s));
    }

    public  void replaceSpaces(char[] str, int length) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = length + spaceCount * 2;
        str[index] = '\0';
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            }
            else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    private String repleaceSpaces(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        int n = s.length();
        int count = 0;
        char[] arr = s.toCharArray();
        for(int i = 0;i < n;i++) {
            if(arr[i] == ' ') {
                count++;
            }
        }

        int newLen = n + count * 2;
        //arr[newLen - 1] = '\0', this is not applicable to Java, so create a new array.
        char[] arr2 = new char[newLen];
        for(int i = n - 1;i >= 0;i--) {
            if(arr[i] == ' ') {
                arr2[newLen - 1] = '0';
                arr2[newLen - 2] = '2';
                arr2[newLen - 3] = '%';
                newLen = newLen - 3;
            } else {
                arr2[newLen - 1] = arr[i];
                newLen = newLen - 1;
            }
        }

        return new String(arr2);
    }


}
