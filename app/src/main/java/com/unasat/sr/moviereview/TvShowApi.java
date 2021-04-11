package com.unasat.sr.moviereview;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unasat.sr.moviereview.dto.TvShowDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TvShowApi {

//    private void getTvShowData() {
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//        final String ALL_TvShows = "http://api.tvmaze.com/search/shows?q=power";
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, ALL_TvShows,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        List<TvShowDto> tvShowDtos = mapJsonToTvShowObject(response);
//                        System.out.println(tvShowDtos);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
//
//
//    private List<TvShowDto> mapJsonToTvShowObject(String jsonArray) {
//        ObjectMapper mapper = new ObjectMapper();
//        List<TvShowDto> tvShowList = new ArrayList<>();
//        List<Map<String, ?>> tvShowArray = null;
//        TvShowDto tvShow = null;
//
//        try {
//            tvShowArray = mapper.readValue(jsonArray, List.class);
//            for (Map<String, ?> map : tvShowArray) {
//                tvShow  = new TvShowDto((String) map.get("show"));
//                tvShowList.add(tvShow);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Er is wat fout gegaan bij het parsen van de json data");
//        }
//        return tvShowList;
//    }



//    public void gettingId(View view) {
//        dataTest1(new Homescreen.TvShowIdResponse() {
//            @Override
//            public void onError(String message) {
//                Toast.makeText(Homescreen.this, "Somtin Wong", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(TvShowDto dto) {
//                Toast.makeText(Homescreen.this, dto.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public interface TvShowIdResponse {
//        void onError(String message);
//
//        void onResponse(TvShowDto dto);
//    }
//
//    public void dataTest1(Homescreen.TvShowIdResponse tvShowIdResponse) {
//        EditText countryN = (EditText) findViewById(R.id.countryName);
//        //RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "https://api.tvmaze.com/shows/" + countryN.getText().toString();
//
//        List<TvShowDto> dtoList = new ArrayList<>();
//        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                TvShowDto dtoTest = new TvShowDto();
//
//                try {
//                    dtoTest.setName(response.getString("name"));
//                    //JSONArray genreslist = response.getJSONArray("genres");
//                    //JSONObject genresApi = (JSONObject) genreslist.get(5);
//                    //dtoTest.setGenres(genresApi.getString("genres"));
//                    dtoTest.setGenres(response.getString("genres"));
//                    dtoTest.setSummary(response.getString("summary"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                tvShowIdResponse.onResponse(dtoTest);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Homescreen.this, "Head no good", Toast.LENGTH_SHORT).show();
//            }
//        });
//        MySingleton.getInstance(this).addToRequestQueue(objectRequest);
//    }
}
