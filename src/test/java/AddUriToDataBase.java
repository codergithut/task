import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tianjian on 2020/3/25.
 */
public class AddUriToDataBase {

    public void main(String[] args) throws IOException {
        List<String> value = Files.readLines(new File("ss"),Charsets.UTF_8);

    }
}
