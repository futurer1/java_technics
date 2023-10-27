package regexp;

/**
 * @url https://www.regexlib.com/CheatSheet.aspx - краткий словарь конструкций
 */
public class Regexp1 {
    public static void main(String[] args) {

        String str = "1234";
        System.out.println("-----1");
        System.out.println(str.matches("1234"));
        System.out.println(str.matches("12345"));
        System.out.println("-----1");

        /*


             \\d цифра
             \\w одна буква
             + один или более
             * 0 или более
             ? 0 или 1 символ до

             ( |  |  |  |)  множество вариантов

             [a-zA-Z] все латинские буквы
             [0-9] все цифры
             [^abc] кроме указанных символов

             . любой символ
             {2} два символа до \\d{2} не более 2 цифр
             {2, } два и более символов
             {3, 5} от 3 до 5 символов
         */

        System.out.println("-----2");
        String str1 = "1634456456";
        System.out.println(str1.matches("\\d+"));

        String str2 = "";
        System.out.println(str2.matches("\\d*"));

        String str3 = "-123123";
        System.out.println(str3.matches("-\\d*"));

        System.out.println("23423423".matches("-?\\d*"));
        System.out.println("-----2");


        System.out.println("-----3");
        System.out.println("+23423423".matches("(-|\\+)?\\d*"));
        System.out.println("-23423423".matches("(-|\\+)?\\d*"));
        System.out.println("23423423".matches("(-|\\+)?\\d*"));
        System.out.println("-----3");

        System.out.println("-----4");
        System.out.println("d23423423".matches("[a-zA-Z]\\d*"));
        System.out.println("-----4");

        System.out.println("-----5");
        System.out.println("dasdASDAD23423423".matches("[a-zA-Z]+\\d*"));
        System.out.println("-----5");

        System.out.println("-----6");
        System.out.println("dasdASDAD222222222225555555555523423423".matches("[a-zA-Z52]+\\d*"));
        System.out.println("-----6");

        System.out.println("-----7");
        System.out.println("Vasya".matches("[^t]*")); // не содержит t
        System.out.println("Petya".matches("[^t]*"));
        System.out.println("-----7");

        System.out.println("-----8");
        System.out.println("http://www.mail.ru".matches("http://www\\..+\\.(com|ru)"));
        System.out.println("https://www.microsoft.com".matches("http(s)?://www\\..+\\.(com|ru)"));
        System.out.println("-----8");

        System.out.println("-----9");
        System.out.println("1234".matches("\\d{2,}")); // от 2 цифр и более
        System.out.println("1234".matches("\\d{2,4}")); // от 2 до 4 цифр
        System.out.println("1234".matches("\\d{4}")); // не более 4 цифр
        System.out.println("-----9");
    }
}
