package com.example.openbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.kb);
        String temp;
        Log.d("hiii","byee");
        next=findViewById(R.id.Next);
        next.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MainActivity2.class)));
        String url="https://j5jbc1hv0h.execute-api.eu-west-1.amazonaws.com/getLastItemList/d195e121-d7b7-454a-bbb1-68be0a15e133";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    JSONObject root_ob=response.getJSONObject("main");
//                    JSONArray arr=response.getJSONArray("weather");
//                    JSONObject object=arr.getJSONObject(0);
//                    String temp= String.valueOf(root_ob.getDouble("temp"));
//                    String description=object.getString("description");
//                    String city=response.getString("name");
//                    Calendar calendar=Calendar.getInstance();
//                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE-MM-dd");
//                    String date=simpleDateFormat.format(calendar.getTime());
                    JSONArray ap=response.getJSONArray("content_list");
                    JSONObject rr=ap.getJSONObject(0);
                    Log.d("hiii","byee");
                    String temp=rr.getString("img_link");
                    JSONArray jsonArray=rr.getJSONArray("obj_list");
                    HashMap<String,String> map=new HashMap<>();
                    String mobileArray[]=new String[jsonArray.length()];
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String temp1=jsonObject.getString("thing_class");
                        String count=jsonObject.getString("count");
                        if(!map.containsKey(temp1)){
                            map.put(temp1,count);
                        }
                        mobileArray[i]=temp1+" : "+count;
                    }
                    Glide.with(MainActivity.this).load(temp).into(imageView);

                    ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this,
                            R.layout.activity_listview, mobileArray);

                    ListView listView = (ListView) findViewById(R.id.mobile_list);
                    listView.setAdapter(adapter);







                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(jsonObjectRequest);
    }
}