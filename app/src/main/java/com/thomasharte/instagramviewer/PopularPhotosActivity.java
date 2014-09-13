package com.thomasharte.instagramviewer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PopularPhotosActivity extends Activity {

    private static final String CLIENT_ID = "f1da4f2aaf9b446d8a3ff2a718557832";
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotoAdaptor aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_photos);

        fetchPopularPhotos();
    }

    private void fetchPopularPhotos() {

        photos = new ArrayList<InstagramPhoto>();
        aPhotos = new InstagramPhotoAdaptor(this, photos);

        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aPhotos);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Please wait...");
        progress.show();
// To dismiss the dialog
        String popularUrl = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(popularUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // response succeeded
                // url, height, username, caption

                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                    for(int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);

                        String caption = null, username, imageUrl, profilePictureUrl;
                        int imageHeight, likesCount;

                        JSONObject captionObject = photoJSON.getJSONObject("caption");
                        if(captionObject != null)
                            caption = captionObject.getString("text");

                        JSONObject userObject = photoJSON.getJSONObject("user");
                        username            = userObject.getString("username");
                        profilePictureUrl   = userObject.getString("profile_picture");

                        likesCount  = photoJSON.getJSONObject("likes")  .getInt     ("count");

                        JSONObject standardResolutionJSON = photoJSON.getJSONObject("images").getJSONObject("standard_resolution");
                        imageUrl    = standardResolutionJSON.getString("url");
                        imageHeight = standardResolutionJSON.getInt("height");

                        if((username != null) && (imageUrl != null) && (imageHeight != 0)) {
                            InstagramPhoto photo = new InstagramPhoto(caption, username, profilePictureUrl, imageUrl, imageHeight, likesCount);
                            photos.add(photo);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                aPhotos.notifyDataSetChanged();
                progress.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
