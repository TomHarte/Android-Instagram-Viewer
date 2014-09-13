package com.thomasharte.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by thomasharte on 11/09/2014.
 */
public class InstagramPhotoAdaptor extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotoAdaptor(Context context, List<InstagramPhoto> objects) {
        super(context, R.layout.item_photo, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        TextView tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
        ImageView imgPhoto = (ImageView)convertView.findViewById(R.id.imgPhoto);
        ImageView imgProfilePhoto = (ImageView)convertView.findViewById(R.id.imgProfilePhoto);

        tvCaption.setText(photo.getCaption());
        tvUsername.setText(photo.getUsername());

        // Instagram photos are always square
        imgPhoto.getLayoutParams().height = imgPhoto.getLayoutParams().width;

        imgPhoto.setImageResource(0);
        imgProfilePhoto.setImageResource(0);

        Picasso.with(getContext()).load(photo.getImageUrl()).into(imgPhoto);
        Picasso.with(getContext()).load(photo.getProfilePictureUrl()).into(imgProfilePhoto);

        return convertView;
    }
}
