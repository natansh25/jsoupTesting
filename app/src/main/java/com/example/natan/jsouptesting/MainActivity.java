package com.example.natan.jsouptesting;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Document doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MyAsyncTask().execute();


    }


    public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            try {

                

                // get title of the page
                String title = doc.title();
                System.out.println("Title: " + title);

//                 get all links
                Elements links = doc.getElementsByClass("price-block-wrapper");


                for (Element link : links) {

                    Elements images = link.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                    Elements ul_li = link.select("ul > li");


                    Elements Date = ul_li.select("span.price");
                    Elements li_first = link.select("ul li:first-child");
                    Elements Event_Location = li_first;
                    Elements Event_Title = ul_li.select("li.event-title");
                    Elements Event_Time = ul_li.eq(2);
                    Elements Event_Phone = ul_li.eq(3);
                    Elements Event_Email = ul_li.eq(4);
                    Elements Event_Address = ul_li.eq(5);
                    Elements Event_Registration = link.select("a[href].type-4");


                    System.out.println("\nImage : " + images.attr("src"));
                    System.out.println("\nDate : " + Date.text());
                    Date.remove();
                    System.out.println("\nLocation :" + Event_Location.text());
                    System.out.println("\nName of sibir : " + Event_Title.text());
                    System.out.println("\nTime : " + Event_Time.text());
                    System.out.println("\nPhone Number :" + Event_Phone.text());
                    System.out.println("\nEmail : " + Event_Email.select("a[href]").attr("href").replace("/cdn-cgi/l/email-protection#",""));
                    System.out.println("\nAddress : " + Event_Address.text());
                    System.out.println("\nRegistration : " + Event_Registration.attr("href"));

//
//
                    System.out.println("\n--------------------------------------------");

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }


    }


//    public String decode(String email) {
//
//
//        String email1 = "", r = Integer.parseInt(encodedString.substr(0, 2), 16), n, i;
//
//        for (n = "2"; encodedString.length - n; n += 2) {
//            i = Integer.parseInt(encodedString.substr(n, 2), 16) ^ r;
//            email += String.fromCharCode(i);
//        }
//        return email;
//    }
}

