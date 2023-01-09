import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import historyobject.king.King;

public class HistoryObjectsTest {
    public static void main(String[] args) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type kingListType = new TypeToken<List<King>>() {
        }.getType();
        List<King> kings = gson.fromJson(new FileReader("src\\main\\resources\\data\\king.json"), kingListType);
        for (King king : kings) {
            // Test: check for null fields after serialization
            // for (Field f : king.getClass().getDeclaredFields()) {
            //     f.setAccessible(true);
            //     if (f.get(king) == null)
            //         System.out.println(king.toString());
            // }
            // Test: check for null fields after lazy loading
            if (king.getThuyHieu() == null){
                System.out.println(king.toString());
            }
        }
    }
}
