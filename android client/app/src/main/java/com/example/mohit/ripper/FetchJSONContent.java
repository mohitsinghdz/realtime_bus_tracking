package com.example.mohit.ripper;



        import android.os.AsyncTask;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.Iterator;

/**
 * Reference from http://stackoverflow.com/questions/33229869/get-json-data-from-url-using-android
 */
public class FetchJSONContent extends AsyncTask<Void, Void, JSONObject>
{ static JSONArray JsonDataFromURL=new JSONArray();

    @Override
    public JSONObject doInBackground(Void... params)
    {

        String str="http://13.85.84.245/getall/"; //This is your JSON URL
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
            Log.e("App", "FetchJSONContent", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(JSONObject response)
    {
        if(response != null)
        {
            try {

                Log.e("App", "Success: " + response.getString("1") );


                //Iterator To parse Json Data
                JSONObject JsonDataFetched= response.getJSONObject("1");
                Iterator x = JsonDataFetched.keys();

                while (x.hasNext()){
                    String key = (String) x.next();
                    JsonDataFromURL.put(JsonDataFetched.get(key));
                }
                //


                MainActivity.tv.setText(
                        "Bus ID: "+JsonDataFromURL.getString(0)
                                +"\nLongitude: "+JsonDataFromURL.getString(1)
                                +"\nLatitude: "+JsonDataFromURL.getString(2)
                                +"\nTime: "+JsonDataFromURL.getString(3));  //This will update Textview on MainActivity

            } catch (JSONException ex) {
                Log.e("App", "Failure", ex);

                MainActivity.tv.setText("Not Able to connect");
            }
        }
    }
}