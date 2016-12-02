package com.example.mikey.maps.Facebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikey.maps.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;


import org.json.JSONException;
import org.json.JSONObject;

public class FacebookSharing extends AppCompatActivity {
    ImageView imageView;
    TextView txtName, txtURL, txtGender,txtBd;
    Button btnShare;

    private ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_sharing);

        shareDialog = new ShareDialog(this);


        //Another way to share content
        btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Oswego County Trails")
                            .setContentDescription("Oswego County Hiking and Biking Trails")
                            .setContentUrl(Uri.parse("http://visitoswegocounty.com/the-great-outdoors/hiking-biking-trails/"))
                            .setImageUrl(Uri.parse("http://visitoswegocounty.com/wp-content/themes/SimplePress/images/logo.png"))
                            .build();

                    shareDialog.show(linkContent);
                }
            }
        });




        //Like
        LikeView likeView = (LikeView) findViewById(R.id.likeView);
        likeView.setLikeViewStyle(LikeView.Style.STANDARD);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);

        likeView.setObjectIdAndType(
                "https://www.facebook.com/visitoswegocounty/", LikeView.ObjectType.OPEN_GRAPH);



        //Share Dialog
        //You cannot preset the shared link in design time, if you do so, the fb share button will
        //look disabled. You need to set in the code as below
        ShareButton fbShareButton = (ShareButton) findViewById(R.id.fb_share_button);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Oswego County Trails")
                .setContentDescription(
                        "Oswego County Hiking and Biking Trails")
                .setContentUrl(Uri.parse("http://visitoswegocounty.com/the-great-outdoors/hiking-biking-trails/"))
                .setImageUrl(Uri.parse("http://visitoswegocounty.com/wp-content/themes/SimplePress/images/logo.png"))

                .build();
        fbShareButton.setShareContent(content);
    }





}