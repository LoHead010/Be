package com.lohead010.be;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.lang.String;
import java.util.List;
import java.util.ArrayList;

import android.widget.Toast;
import android.content.Context;


public class HomeActivity extends Activity {
    public List<Words> verbs = new ArrayList<Words>();
    int min = 0;
    int max = 5;
    int count = 0;
    String result = "";
    //int idex = (int) (Math.random() * ((max - min) + 1));
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            final MySQLiteHelper db = new MySQLiteHelper(this);
            db.deleteAllRecords();

            addVerb(db);
            // final TextView tvWord = (TextView)findViewById(R.id.tvResults);
            final TextView tvBe = (TextView) findViewById(R.id.tvBe);
            tvBe.setOnClickListener(new View.OnClickListener() {


                int idex = (int) (Math.random() * ((max - min) + 1));
                @Override
                public void onClick(View v) {



                    final int dex = idex;
                    if (count == 0) {

                        TextView be = (TextView) findViewById(R.id.tvBe);
                        String x = Integer.toString(count);
                        String edit = "" + be.getText();


                        //final int idex = (int) (Math.random() * ((max - min) + 1));

                        //this selectWord method should return the query results and display
                        selectWord(db,dex);


                        String verb = verbs.get(dex).getWord();
                        be.setTypeface(null, Typeface.BOLD_ITALIC);
                        be.setText("Be " + verb + ".");

                        //verbadd();

                        count++;
                        //Toast.makeText(this,count  , Toast.LENGTH_LONG)
                    } else {
                        TextView be = (TextView)findViewById(R.id.tvBe);

                        String edit = "" + be.getText();
                        be.setText(verbs.get(dex).getDefinition());
                        count = 0;
                        idex = (int) (Math.random() * ((max - min) + 1));

                        //define();
                    }
                }
            });
            displayCurrentRecords(db, tvBe);
        }
        catch (Exception E){
            String er = E.toString();
            Toast.makeText(this, er , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        //not sure how to fix this? This is new...
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addVerb(MySQLiteHelper db) {
        //this will be an array of words. wanna do the whole list (200) and add them all here
        int count = verbs.size();
        Words[] word = new Words[5];
        //Toast.makeText(this, "addverb db" , Toast.LENGTH_LONG).show();
        for (int i = 0; i < 5; i++) {
            Words temp = word[i];
            temp = new Words();
            word[0] = new Words(0, "Savvy.", "Comprehend or" + "\n" + "Understanding");
            word[1] = new Words(1, "Thankful.", "feeling or showing thanks");
            word[2] = new Words(2, "Grateful.", "appreciative of " + "\n" + "benefits received");
            word[3] = new Words(3, "Selfless", "having no concern for self");
            word[4] = new Words(4, "Rewarding", "giving satisfaction");

            db.addVerb(word);

        }
    }

    public void selectWord(MySQLiteHelper db,int dex ){



        int in = dex;



        result += db.selectWord(in);
        // TextView test = (TextView)findViewById(R.id.tvr);
        //test.setText(result);

        Toast.makeText(this, "in selectWord", Toast.LENGTH_LONG).show();

    }
            /*word[]
            ContentValues values = new ContentValues();
            values.put(KEY_)*/


    public void displayCurrentRecords(MySQLiteHelper db, TextView tvBe)
    {
        //Toast.makeText(this, "in displaycurrent" , Toast.LENGTH_LONG).show();
        verbs.clear();
        verbs = db.getVerb();
        String strDisplay = "\n\n";
        if (!verbs.isEmpty())
        {
            for (int i = 0; i < verbs.size(); i++)
            {
                //strDisplay += verbs.get(i).toString() + "\n";
            }

            // tvPeople.setText(strDisplay);
        }
        else
        {
            tvBe.setText("");
        }
    }



    public void displayword(){

        try {
            int count = 0;
            String x = Integer.toString(count);
            TextView be = (TextView) findViewById(R.id.tvBe);

            String edit = "" + be.getText();
            String verb = "Aware";
            be.setTypeface(null, Typeface.BOLD_ITALIC);
            be.setText(edit + " Aware.");
            count++;

        }
        catch(Exception e){
            String er = e.toString();
            Toast.makeText(this, er, Toast.LENGTH_LONG).show();
        }

    }
    public void define(){
        TextView be = (TextView)findViewById(R.id.tvBe);

        String edit = "" + be.getText();
        //be.setText(verbs.get(idex).getDefinition());


    }
}
