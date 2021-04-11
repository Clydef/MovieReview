package com.unasat.sr.moviereview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.navigation.NavigationView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.unasat.sr.moviereview.dto.TvShowDto;
import com.unasat.sr.moviereview.fragment.ProfileFragment;
import com.unasat.sr.moviereview.fragment.GenresFragment;
import com.unasat.sr.moviereview.fragment.RatingAndReviewFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Homescreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    private ImageView img;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    TextView tvShowText;
    String imgUrl;

    String aJsonString = "";
    ListView lv_tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        lv_tvShow = (ListView) findViewById(R.id.lv_tvShow);

        tvShowText = (TextView) findViewById(R.id.tvShowTxt);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GenresFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_genres:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GenresFragment()).commit();
                break;

            case R.id.nav_rating_and_review:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RatingAndReviewFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logoutUser) {
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void logoutUser() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void gettingId(View view) {

//        Service service = new Service();
//
//        service.startToast();

        dataTest1(new Homescreen.TvShowIdResponse() {
            @Override
            public void onError(String message) {
                Toast.makeText(Homescreen.this, "Somtin Wong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(TvShowDto dto) {
                TvShowDto textViewTest = new TvShowDto();
                Toast.makeText(Homescreen.this, dto.toString(), Toast.LENGTH_LONG).show();

                //tvShowText.setText(dto);
//                ArrayAdapter arrayAdapter = new ArrayAdapter(Homescreen.this, android.R.layout.simple_list_item_1, dto);
//                lv_tvShow.setAdapter(arrayAdapter);
                //tvShowText.append(dto.toString());
 //               String content = "";
//                content += "Summary: " + textViewTest.getSummary() + "\n";
//                tvShowText.append(content);

            }
        });
        displayOnlineImg();
//        String imageUri = "https://static.tvmaze.com/uploads/images/medium_portrait/204/511371.jpg";
//        MySingleton.getInstance(this).getImageLoader(imageUri);
        //imageLoaderS.DisplayImage(//your image url, //your imageView);
    }

    public interface TvShowIdResponse {
        void onError(String message);

        void onResponse(TvShowDto dto);
    }

    public void dataTest1(Homescreen.TvShowIdResponse tvShowIdResponse) {
        EditText tvShowN = (EditText) findViewById(R.id.tvShowName);
        //RequestQueue queue = Volley.newRequestQueue(this); // Singleton takes the place
        String url = "https://api.tvmaze.com/singlesearch/shows?q=" + tvShowN.getText().toString();

        //List<TvShowDto> dtoList = new ArrayList<>();
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                List<TvShowDto> tvShowDtos = response;
//
//                for (TvShowDto tvShowDto1 : tvShowDtos) {
//                    String content = "";
//                    content += "Name: " + tvShowDto1.getName() + "\n";
//                    content += "Summary: " + tvShowDto1.getSummary() + "\n\n";
//                    tvShowText.append(content);
//                }

                TvShowDto dtoTest = new TvShowDto();
                TvShowDto dtotxt = new TvShowDto();

                try {
                    JSONObject jsonobject1 = response.getJSONObject("image");
                    imgUrl = jsonobject1.getString("medium");

                    dtoTest.setName(response.getString("name"));

                    dtoTest.setGenres(response.getString("genres"));

                    JSONObject jsonobject = response.getJSONObject("rating");
                    String finalString = jsonobject.getString("average");
                    dtoTest.setRating(finalString);

                    dtoTest.setSummary(response.getString("summary"));

//                    String in = null;
//                    JSONObject reader = new JSONObject(in);
//                    JSONObject sys  = reader.getJSONObject("rating");
//                    dtotxt.setRating(sys.getString("average"));
                    
                    //dtoList.add(dtoTest);

//                    JSONObject jObject = new JSONObject();
//                    aJsonString = jObject.getString("name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tvShowIdResponse.onResponse(dtoTest);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Homescreen.this, "Head no good", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(objectRequest);
    }

    public void displayOnlineImg() {
        // initialize image loader before using
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        Boolean isThereImage = imageLoader.isInited(); // Attemped to destroy image for next one

        // initialize imageview from activity_main.xml
        img = findViewById(R.id.tvShowImage);

        // URL for our image that we have to load..
        String imageUri = "https://static.tvmaze.com/uploads/images/medium_portrait/204/511371.jpg"; // This was a test

        // with below method we are setting display option for our image..
        options = new DisplayImageOptions.Builder()
                // imageOnLoading will display when your image is loading
                .showImageOnLoading(R.drawable.ic_launcher_foreground)
                // below image will be displayed when the image url is empty
                .showImageForEmptyUri(R.drawable.ic_launcher_background)
                // build will build the view for displaying image..
                .build();
        // below method will display image inside our image view..
        imageLoader.displayImage(imgUrl, img, options, null);
    }

//    private List<TvShowDto> mapJsonToTvShowObject(String jsonArray) {
//        ObjectMapper mapper = new ObjectMapper();
//        List<TvShowDto> tvShowList = new ArrayList<>();
//        List<Map<String, ?>> tvShowArray = null;
//        TvShowDto showDto = null;
//
//        try {
//            tvShowArray = mapper.readValue(jsonArray, List.class);
//            for (Map<String, ?> map : tvShowArray) {
//                showDto = new TvShowDto((String) map.get("name"), (String) map.get("summary"));
//                tvShowList.add(showDto);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Er is wat fout gegaan bij het parsen van de json data");
//        }
//        return tvShowList;
//    }
}