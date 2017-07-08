package com.webaculous.pro.lecturenotifier;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends android.support.v4.app.Fragment {
    TextView textView;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TimeTableAdapter timeTableAdapter;
    ListView listView;
    TextView tv;

    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.page_fragment_layout,container,false);
        listView = (ListView) view.findViewById(R.id.idListView);
        tv = (TextView)view.findViewById(R.id.load_title);
        timeTableAdapter = new TimeTableAdapter(getActivity(),R.layout.listview_layout);
        listView.setAdapter(timeTableAdapter);
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        new BackTask().execute(message);
        return view;
    }

    class BackTask extends AsyncTask<String,Void,String>
    {
        String show_url;
        String json_data;
        @Override
        protected void onPreExecute() {
            show_url = "http://webaculous.000webhostapp.com/show.php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String id = params[0];
                URL url = new URL(show_url+"?id="+id);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                while((json_data = bufferedReader.readLine())!=null)
                {
                    result.append(json_data+"\n");
                }
                bufferedReader.close();;
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString().trim();
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return  null;


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            tv.setVisibility(View.INVISIBLE);
            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("result");
                int count=0;
                String start,end,day,lecture,teacher,room;

                while(count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    start = JO.getString("start");
                    end = JO.getString("end");
                    day = JO.getString("day");
                    lecture = JO.getString("subject");
                    teacher = JO.getString("teacher");
                    room = JO.getString("location");
                    TimeTable timeTable = new TimeTable(start,end,day,lecture,teacher,room);
                    timeTableAdapter.add(timeTable);
                    count++;


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

}

