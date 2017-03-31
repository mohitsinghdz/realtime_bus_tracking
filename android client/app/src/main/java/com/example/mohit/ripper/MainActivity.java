package com.example.mohit.ripper;



        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.widget.TextView;

        import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "How app works etc. info here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Assigning Values
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView);

        // 1) Fetch Location Details
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchJSONContent().execute(); //This will launch HTTP web request & Update our textview with location details.

                Snackbar.make(v, "Background Service Started", Snackbar.LENGTH_LONG) //Just for notification
                        .setAction("Action", null).show();
            }
        });

        //2) Launch Map
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Reference from http://stackoverflow.com/questions/33118354/json-url-contains-latlng-how-to-show-it-on-google-map

                try {
                    Intent intent = null;
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=&daddr="
                                    + FetchJSONContent.JsonDataFromURL.getString(1) + "," + FetchJSONContent.JsonDataFromURL.getString(2)));

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setClassName("com.google.android.apps.maps",
                            "com.google.android.maps.MapsActivity");
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

   // @Override
  //  public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
  //      getMenuInflater().inflate(R.menu.menu_main, menu);
   //     return true;
  //  }

 //   @Override
 //   public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
 //       int id = item.getItemId();

        //noinspection SimplifiableIfStatement
 //       if (id == R.id.action_settings) {
   //         return true;
 //       }

  //      return super.onOptionsItemSelected(item);
    //}
//}

