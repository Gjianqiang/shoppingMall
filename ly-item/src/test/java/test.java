import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class test {
    public static void main(String[] args) {
        try {

            Class clazz=Class.forName("com.leyou.item.pojo.Stock");
            Method[] method = clazz.getDeclaredMethods();//获取对象方法
            Field[] declaredFields = clazz.getDeclaredFields();//获取对象属性
            System.out.println("对象方法:");
            for (Method method1 : method) {
                System.out.println(method1.toString());
            }
            System.out.println("对象属性:");
            for (Field field : declaredFields) {
                System.out.println(field.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
