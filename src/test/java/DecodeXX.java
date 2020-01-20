import com.factory.task.util.QRCodeUtil;

/**
 * Created by tianjian on 2020/1/20.
 */
public class DecodeXX {
    public static void main(String[] args) throws Exception {
        String str = QRCodeUtil.decode("/Users/tianjian/IdeaProjects/task/src/test/java/test.png");
        // 打印出解析出的内容
        System.out.println(str);

    }
}
