package by.it.hryshanovich.jd01_06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB1 {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder(Poem.text);
        Pattern pattern=Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher matcher= pattern.matcher(Poem.text);
        while (matcher.find()){
            String word = matcher.group();
            //if (cheskWord(word))
        }
    }
}
