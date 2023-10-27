package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp3 {
    public static void main(String[] args) {
        String str = "aaa bbb ccc sldfjlsdjf lskdjf ls dflskd test1@gmail.com flsjd flsdj flskjdf slkj\n" +
                "sldkf sldkfj lsdkfj lskdfj lsdjf lskjdf lksjd flsj df" +
                " slkdjf lskjdf test@mail.ru owie ksdjflkwjeljweoifj  lksjdlfk sdlfkj ";

        Pattern email = Pattern.compile("([a-zA-z0-9]+)@(gmail|mail)\\.(com|ru)");
      
        Matcher matcher = email.matcher(str);
      
        while (matcher.find()) { // итерируется по найденным совпадениям
          
            // получение группы без id вернет полную последовательность, совпавшую с паттерном
            System.out.println(matcher.group()); // test1@gmail.com test@mail.ru

            // возможность получить отдельную группу из паттерна          
            System.out.println(matcher.group(1)); // test1 test
            System.out.println(matcher.group(2)); // gmail mail
            System.out.println(matcher.group(3)); // com ru
        }
    }
}
