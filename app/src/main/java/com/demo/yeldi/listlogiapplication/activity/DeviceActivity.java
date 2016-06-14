package com.demo.yeldi.listlogiapplication.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.demo.yeldi.listlogiapplication.R;
import com.demo.yeldi.listlogiapplication.Utils.AppController;
import com.demo.yeldi.listlogiapplication.Utils.Global_values;
import com.demo.yeldi.listlogiapplication.Utils.MyProgress;
import com.demo.yeldi.listlogiapplication.adapter.DeviceListAdapter;
import com.demo.yeldi.listlogiapplication.data.DeviceItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeviceActivity extends AppCompatActivity {

    private static String TAG = DeviceActivity.class.getSimpleName();
    MyProgress myprogress;
    public ArrayList<DeviceItem> deviceTypeItem;
    Dialog dialog;
    ListView mListdevice;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myprogress = new MyProgress(DeviceActivity.this, "Processing ....");
                myprogress.showProgress();
                GetDevicelist();
            }
        });

        deviceTypeItem = new ArrayList<DeviceItem>();

    }


    private void GetDevicelist() {

        // TODO Auto-generated method stub
        String url = Global_values.Urlstr;

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.device_list);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    if (response != null) {

                        deviceTypeItem.clear();
                            JSONArray jsonArray = response.optJSONArray("devices");
                        for(int i=0; i < jsonArray.length(); i++){

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            DeviceItem deviceDetails = new DeviceItem();
                            deviceDetails.setDevicType(jsonObject.getString("deviceType"));
                            deviceDetails.setModel(jsonObject.getString("model"));
                            deviceDetails.setName(jsonObject.getString("name"));
                            // adding device details array
                            deviceTypeItem.add(deviceDetails);
                        }
                        mListdevice = (ListView) dialog.findViewById(R.id.list_state);
                        DeviceListAdapter stateListAdapter = new DeviceListAdapter(getApplicationContext(), deviceTypeItem);
                        mListdevice.setAdapter(stateListAdapter);
                        myprogress.hideProgress();
                        dialog.show();
                    }

                } catch (Exception ex) {
                    myprogress.hideProgress();
                    Log.d("Exception", ex.toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error.getMessage());
                myprogress.hideProgress();
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


}
