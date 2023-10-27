package regexp;

/**
 * @url https://www.regexlib.com/CheatSheet.aspx - краткий словарь конструкций
 */
public class Regexp2 {
    public static void main(String[] args) {
        String s = "aaa bbb ccc";
        String[] arr = s.split(" ");
        System.out.println(arr.length); // 3

        String s1 = "aaa234234234bbb34634634ccc125235235ddd";
        String[] arr1 = s1.split("\\d+");
        System.out.println(arr1.length); // 4

        String s2 = "aaa234234234bbb34634634ccc125235235";
        String res = s2.replaceAll("\\d+", " ");
        System.out.println(res); // aaa bbb ccc

        String s3 = "aaa234234234bbb34634634ccc125235235";
        String res1 = s3.replaceFirst("\\d+", " "); // заменит только первое вхождение
        System.out.println(res1); // aaa bbb34634634ccc125235235
    }
}
