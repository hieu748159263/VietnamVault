import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import historyobject.festival.Festival;

public class HistoryObjectsTest {
    public static void main(String[] args) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<List<Festival>>() {
        }.getType();
        List<Festival> objs = gson.fromJson(new FileReader("src\\main\\resources\\data\\festival.json"), type);
        for (Festival obj : objs) {
            System.out.println(obj.getFestivalName());
        }
    }
}
