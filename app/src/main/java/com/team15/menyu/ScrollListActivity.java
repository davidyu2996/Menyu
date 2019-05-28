package com.team15.menyu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ScrollListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//    //Programmatically fill in menu order options
//<<<<<<< HEAD
//    DisplayMetrics metrics = getResources().getDisplayMetrics();
//    float dp = 80f;
//    float fpixels = metrics.density * dp;
//    int pixels = (int) (metrics.density * dp + 0.5f);
//
//    LinearLayout list = (LinearLayout) findViewById(R.id.scrollingListTEMP);
//    int listItems = 6; //HOW MANY PLACES
//    String[] itemTitles = {"Ayy lmao", "Dank Memes", "Jet Fuel with a side of Steel Beams", "4", "5", "6"}; //DAVID convert from your PlaceLikelihood
//    String[] itemReviews = {"/%d reviews", "'d101 reviews", "f240 reviews", "444", "555", "666"}; //DAVID same
//
//    for(int i = 0; i < listItems; i++) {
//        RelativeLayout listBox = new RelativeLayout(this);
//        listBox.setBackgroundColor(Color.LTGRAY);
//        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, //width?
//                pixels);                                  //height?
//        listBox.setLayoutParams(rlp);
//
//        final int temp1 = i;
//        listBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "no touchy me" + temp1, Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });
//
//        // Defining the layout parameters of the TextView
//        RelativeLayout.LayoutParams lpTitle = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        lpTitle.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//
//        // Setting the RelativeLayout as our content view
//        //setContentView(listBox, rlp); //IDK WHAT THIS DOES BESIDES CAUSE APP TO CRASH
//
//        TextView title = new TextView(this);
//        title.setText(itemTitles[i]);
//        title.setTextAppearance(this, android.R.style.TextAppearance_Large);
//        title.setTextColor(Color.BLACK);
//        title.setPadding(8, 8, 8, 4);
//        title.setId(i+1); //setID has to have positive Int as parameter
//        title.setLayoutParams(lpTitle);
//        listBox.addView(title);
//
//        RelativeLayout.LayoutParams lpReviews = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        lpReviews.addRule(RelativeLayout.BELOW, i+1); //apparently this is better
//                                                      //than title.getId()
//
//        TextView reviews = new TextView(this);
//        reviews.setLayoutParams(lpReviews);
//        reviews.setText(itemReviews[i]);
//        reviews.setTextSize(14);
//        title.setTextAppearance(this,android.R.style.TextAppearance_Medium);
//        reviews.setTextColor(Color.DKGRAY);
//        reviews.setPadding(8, 4, 8, 8);
//        reviews.setId(i + 101);
//        listBox.addView(reviews);
//
//        list.addView(listBox);
//    }
//
//=======
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dp = 70f;
        int pixels = (int) (metrics.density * dp + 0.5f);
        float dpBorder = 2f;
        int pixelBorder = (int) (metrics.density * dpBorder + 0.5f);

        LinearLayout list = (LinearLayout) findViewById(R.id.scrollingListTEMP);
        int listItems = 6; //HOW MANY PLACES
        String[] itemTitles = {"Ayy lmao", "Dank Memes", "Jet Fuel with a side of Steel Beams", "4", "5", "6"}; //DAVID convert from your PlaceLikelihood

        TextView restaurant = (TextView) findViewById(R.id.pick_location);
        restaurant.setText("Pick Your Location");

        for(int i = 0; i < listItems; i++) {
            RelativeLayout listBox = new RelativeLayout(this);
            listBox.setBackgroundColor(Color.rgb(236, 240, 241));//clouds color
            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, //width?
                    pixels);                                  //height?
            listBox.setLayoutParams(rlp);

            // Defining the layout parameters of the TextView
            RelativeLayout.LayoutParams lpTitle = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            lpTitle.addRule(RelativeLayout.CENTER_IN_PARENT);

            // Setting the RelativeLayout as our content view
            //setContentView(listBox, rlp); //IDK WHAT THIS DOES BESIDES CAUSE APP TO CRASH

            TextView title = new TextView(this);
            title.setText(itemTitles[i]);
            title.setTextSize(20);
            title.setTextColor(Color.BLACK);
            title.setPadding(8, 8, 8, 4);
            title.setId(i + 1); //setID has to have positive Int as parameter
            title.setLayoutParams(lpTitle);
            listBox.addView(title);


            RelativeLayout border = new RelativeLayout(this);
            border.setBackgroundColor(Color.rgb(241, 196, 15));//pumpkin color
            RelativeLayout.LayoutParams rlpborder = new RelativeLayout.LayoutParams(
                    (RelativeLayout.LayoutParams.MATCH_PARENT), //width?
                    pixelBorder);//height?

            border.setLayoutParams(rlpborder);

            list.addView(border);

            list.addView(listBox);
            //Need to add buttons on side
        }
    }

}