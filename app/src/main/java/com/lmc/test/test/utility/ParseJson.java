package com.lmc.test.test.utility;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by lmarathchathu on 12/7/2015.
 */
public class ParseJson {

    public ArrayList<Details> parsejson(InputStream in){
        ArrayList<Details> list=new ArrayList<Details>();

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
            try {
                reader.beginArray();

                while(reader.hasNext()){

                        reader.beginObject();
                    Details details=new Details();
                        while(reader.hasNext()){

                                String name =reader.nextName();

                                if(name.equals("title")){
                                    details.title=reader.nextString();
                                }
                                else if(name.equals("description")){
                                    details.description=reader.nextString();
                                }

                                else{
                                    reader.skipValue();
                                }

                        }
                    list.add(details);
                        reader.endObject();




                }

                reader.endArray();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    return list;
    }




}
