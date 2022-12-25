import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import historyObjects.King;

public class KingTest {
    public static void main(String[] args) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type kingListType = new TypeToken<List<King>>() {}.getType();
        List<King> kings = gson.fromJson(new FileReader("src\\main\\resources\\data\\king.json"), kingListType);
        System.out.println(kings.get(0).toString());
    }
}
