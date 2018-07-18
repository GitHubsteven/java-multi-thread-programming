package Helper;

/**
 * Created by rongbin.xie on 2018/3/10.
 */
public class Template {
    public static void main(String[] args) {
          String template = "MyThread t%s = new MyThread(%s);";
        createCode(template,20);
        template = "t%s.start();";
        createCode(template,20);
    }

    public static void createCode(String template, int size) {
        for (int i = 10; i < size; i++) {
            System.out.println( template.replaceAll("%s", "" + i));
        }
    }
}
