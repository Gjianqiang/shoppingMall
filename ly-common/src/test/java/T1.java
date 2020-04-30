import java.text.SimpleDateFormat;
import java.util.Date;

public class T1 {
    public static void main(String[] args) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cornExpression = formatter.format(new Date());
        System.out.println(cornExpression);

    }

}
