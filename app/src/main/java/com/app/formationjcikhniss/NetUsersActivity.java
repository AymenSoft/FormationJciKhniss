package com.app.formationjcikhniss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONArrayRequestListener;
import com.app.formationjcikhniss.adapters.NetUsersAdapter;
import com.app.formationjcikhniss.models.NetUsers;
import com.google.gson.JsonParseException;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

/**
 * get users from server
 * @author Aymen Masmoudi[04.04.2021]
 * */
public class NetUsersActivity extends AppCompatActivity {

    private ListView lvNetUsers;

    private NetUsersAdapter adapter;
    private ArrayList<NetUsers> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_users);

        lvNetUsers = findViewById(R.id.lv_net_users);

        //get net users from server
        getNetUsers();

    }

    //get net users from server
    private void getNetUsers(){
        AndroidNetworking.get(Webservices.USERS_LIST)
                .build()
                .getAsOkHttpResponseAndJSONArray(new OkHttpResponseAndJSONArrayRequestListener() {
                    @Override
                    public void onResponse(Response okHttpResponse, JSONArray response) {
                        if (okHttpResponse.code() == 200) {
                            String data = response.toString();
                            arrayList = getNetUsersFromJson(data);
                            if (!arrayList.isEmpty()) {
                                adapter = new NetUsersAdapter(NetUsersActivity.this, R.layout.item_net_users, arrayList);
                                lvNetUsers.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("errorCode", anError.getErrorCode()+"");
                        Log.e("errorMessage", anError.getErrorBody());
                    }
                });
    }

    //parse data
    private ArrayList<NetUsers> getNetUsersFromJson(String json){
        ArrayList<NetUsers> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                NetUsers s = mapper.readValue(jsonObject.toString(), NetUsers.class);
                list.add(s);
            }
        } catch (JSONException | JsonParseException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}