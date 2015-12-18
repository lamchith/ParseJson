package com.lmc.test.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.lmc.test.test.ui.DetailsAdapter;
import com.lmc.test.test.utility.Details;
import com.lmc.test.test.utility.ParseJson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

      /*//  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        /*final ListView listview = (ListView)  findViewById(R.id.list_view);


        final ArrayList<Details> list = new ArrayList<Details>();
        //for (int i = 0; i < values.length; ++i) {

        Details detials=new Details();
        detials.description="test";
        detials.title="test1";

            list.add(detials);
      //  }
        DetailsAdapter  adapter=new DetailsAdapter(this,list);
        listview.setAdapter(adapter);*/



    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            new DownloadTask().execute(new URL("https://gist.githubusercontent.com/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json"));

            } catch (MalformedURLException e) {
                  e.printStackTrace();
            } catch (IOException e) {
                  e.printStackTrace();
    }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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



    private class DownloadTask extends AsyncTask<URL, Integer, ArrayList<Details>> {
        protected  ArrayList<Details> doInBackground(URL... urls) {

            ParseJson parseJson= new ParseJson();
            ArrayList<Details> list=null;
            URL url = null;
            try {
                url = urls[0];;
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                list=parseJson.parsejson(in);
                Log.i("Lamchith", "got the list" + list.size());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
                return list;
        }


        protected void onPostExecute(ArrayList<Details> list) {
            final ListView listview = (ListView)  findViewById(R.id.list_view);
            DetailsAdapter  adapter=new DetailsAdapter(MainActivity.this,list);
            listview.setAdapter(adapter);


        }
    }
}
