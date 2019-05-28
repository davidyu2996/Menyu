package com.team15.menyu;

/**
 * Created by RohitRamesh on 2/20/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MenYu.db";

    // Table Names
    private static final String TABLE_FOOD = "Food";
    private static final String TABLE_REVIEW = "Review";

    // Common column names
    private static final String id = "_id";
    private static final String food = "food";
    private static final String restaurant = "resName";

    //Food Table - column nmaes
    private static final String upvotes = "upvotes";
    private static final String downvotes = "downvotes";
    private static final String noOfReviews = "noOfReviews";

    //Review Table - column names
    private static final String helpful = "helpful";
    private static final String reviewText = "reviewText";
    private static final String author = "author";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE "
            + TABLE_FOOD + "(" + id + " INTEGER PRIMARY KEY, " + food
            + " TEXT, " + restaurant + " TEXT, " + upvotes
            + " INTEGER, " + downvotes + " INTEGER, " + noOfReviews +
            " INTEGER" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_REVIEW = "CREATE TABLE " + TABLE_REVIEW
            + "(" + id + " INTEGER PRIMARY KEY, " + food + " TEXT, "
            + restaurant + " TEXT, " + helpful + " INTEGER, " + reviewText + " TEXT, "
            + author + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_REVIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);

        // create new tables
        onCreate(db);
    }

    public ArrayList<Food> getFood(String restaurant) {
        ArrayList<Food> f = new ArrayList<Food>();
        db = this.getReadableDatabase();
        String query = "SELECT food, upvotes, downvotes, noOfReviews FROM " + TABLE_FOOD + " WHERE resName = " + "'" + restaurant + "'";
        Cursor c = db.rawQuery(query, null);
//        Log.d("lol", c.toString());
        if (c.getCount() == 0) {
            return f;
        }
        else {
            c.moveToFirst();
            do {
                String food = c.getString(0);
                int upvotes = c.getInt(1);
                int downvotes = c.getInt(2);
                int noOfReviews = c.getInt(3);
                Food temp = new Food(upvotes, downvotes, noOfReviews, food);
                f.add(temp);
            } while (c.moveToNext());
            return f;
        }
    }

    public void updateVotes(String restaurant, String food, boolean upvote, boolean downvote) {
        db = this.getReadableDatabase();
        int upvotes, downvotes, noOfReviews;
        int id;
        String query = "SELECT upvotes, downvotes, _id FROM " + TABLE_FOOD + " WHERE resName = " + "'" + restaurant + "' AND food = '" + food + "'";
        Cursor c = db.rawQuery(query, null);
        Log.d("lol", c.toString());
        Log.d("lol", String.format("Place %d", c.getCount()));
        if (c.getCount() == 0) {
            return;
        }
        else {
            c.moveToFirst();
            do {
                upvotes = c.getInt(0);
                downvotes = c.getInt(1);
                id = c.getInt(2);
            if(upvote == true) {
                upvotes = upvotes + 1;
                Log.d("lol", String.format("Vote %d %d", upvotes, id));
            }
            else if(downvote == true) {
                downvotes = downvotes + 1;
            }
            db = this.getWritableDatabase();
            ContentValues val = new ContentValues();
            val.put("upvotes", upvotes);
            val.put("downvotes", downvotes);
            db.update(TABLE_FOOD, val, "_id=" + id, null);
            //db.close();
            } while (c.moveToNext());
        }
    }

    public void addFood(Food f) {
        db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(restaurant, f.getName());
        val.put(food, f.getFood());
        val.put(upvotes, f.getUpVotes());
        val.put(downvotes, f.getDownVotes());
        val.put(noOfReviews, f.getNoOfReviews());
        db.insert(TABLE_FOOD, null, val);
        db.close();
    }

    public void addReview(Review r) {
        db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(restaurant, r.getName());
        val.put(food, r.getFood());
        val.put(helpful, r.getHelpful());
        val.put(author, r.getAuthor());
        val.put(reviewText, r.getReview());
        db.insert(TABLE_REVIEW, null, val);
        db.close();
    }

    public ArrayList<Review> getReview(String restaurant, String food) {
        ArrayList<Review> f = new ArrayList<Review>();
        db = this.getReadableDatabase();
        String query = "SELECT helpful, reviewText, author FROM " + TABLE_REVIEW + " WHERE resName = " + "'" + restaurant + "' AND food = '" + food + "'";
        Cursor c = db.rawQuery(query, null);
        Log.d("lol", c.toString());
        if (c.getCount() == 0) {
            return f;
        }
        else {
            c.moveToFirst();
            do {
                int helpful = c.getInt(0);
                String reviewText = c.getString(1);
                String author = c.getString(2);
                Review temp = new Review(helpful, reviewText, author);
                f.add(temp);
            } while (c.moveToNext());
            return f;
        }
    }

    public void updateHelpful(Review r) {
        int helpful, id;
        db = this.getReadableDatabase();
        String query = "SELECT helpful, id FROM " + TABLE_REVIEW + " WHERE resName = '" + r.getName() + "' AND food = '" + r.getFood() + "' AND author = '" + r.getAuthor() + "'";
        Cursor c = db.rawQuery(query, null);
        Log.d("lol", c.toString());
        if(c.getCount() == 0) {
            return;
        }
        else {
            c.moveToFirst();
            do {
                helpful = c.getInt(0);
                id = c.getInt(1);
                helpful = helpful + 1;
            } while (c.moveToNext());
            db = this.getWritableDatabase();
            ContentValues val = new ContentValues();
            val.put("helpful", helpful);
            db.update(TABLE_REVIEW, val, "_id=" + id, null);
            db.close();
        }
    }

}