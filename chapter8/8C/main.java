/*\

Буквенная запись телефонных номеров основана на том, что каждой цифре 
соответствует несколько английских букв: 2 — ABC, 3 — DEF, 4 — GHI, 
5 — JKL, 6 — MNO, 7 — PQRS, 8 — TUV, 9 — WXYZ. Написать програм- 
му, которая находит в заданном телефонном номере подстроку максималь- 
ной длины, соответствующую слову из словаря. 
*/


import java.util.*;

public class Main {

    static Map<Character, Character> map = new HashMap<>();

    public static void main(String[] args) {

        init();

        String number = "4355696753";

        String[] dict = {"hello", "gel", "dog", "world", "joke"};

        String best = "";

        for (String word : dict) {
            String num = toNumber(word);
            if (number.contains(num)) {
                if (word.length() > best.length()) {
                    best = word;
                }
            }
        }

        System.out.println(best);
    }

    static void init() {
        put("abc", '2');
        put("def", '3');
        put("ghi", '4');
        put("jkl", '5');
        put("mno", '6');
        put("pqrs", '7');
        put("tuv", '8');
        put("wxyz", '9');
    }

    static void put(String letters, char digit) {
        for (char c : letters.toCharArray()) {
            map.put(c, digit);
        }
    }

    static String toNumber(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toLowerCase().toCharArray()) {
            sb.append(map.get(c));
        }
        return sb.toString();
    }
}
