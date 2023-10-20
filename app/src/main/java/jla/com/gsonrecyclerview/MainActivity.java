package jla.com.gsonrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import jla.com.gsonrecyclerview.Model.Person;
import jla.com.gsonrecyclerview.Model.PersonAdapter;

public class MainActivity extends AppCompatActivity {

    List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        setRecyclerView();
        getJsonList();
    }

    List<Person> getGson() throws IOException, JSONException {
        URL url = new URL("https://reqres.in/api/users?page=2");
//        String url = ;
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream inputstream = connection.getInputStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(inputstream));
        String line;
        String text = "";
        while ((line = rd.readLine()) != null) {
            text += line;
        }
        JSONObject obj = null;
        obj = new JSONObject(text);
        //String username = obj.getJSONObject("author").getString("username");
        JSONArray posts = obj.getJSONArray("data");
        Gson gson = new Gson(); // khởi tạo Gson
        Person[] list = gson.fromJson(posts.toString(), Person[].class);
        Log.i("datahhhhhhhh", "" + list.length);
        return Arrays.asList(list);

    }

    public void getJsonList() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (this) {
                    try {
                        persons = getGson();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecyclerView rec = findViewById(R.id.recycle_view);
                                rec.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                rec.setAdapter(new PersonAdapter(persons, MainActivity.this));
                            }
                        });
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        t.start();
    }
}