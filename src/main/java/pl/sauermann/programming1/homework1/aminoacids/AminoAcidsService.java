package pl.sauermann.programming1.homework1.aminoacids;

import java.util.Arrays;

public class AminoAcidsService {

    public static boolean changePossible(String s1, String s2) {
        if (s1 != null || s2 != null || s1.length() == s2.length()) {
            char[] firstWordArray = s1.toCharArray();
            char[] secondWordArray = s2.toCharArray();
            Arrays.sort(firstWordArray);
            Arrays.sort(secondWordArray);
            return (Arrays.equals(firstWordArray, secondWordArray));
        } else {
            return false;
        }
    }
}
