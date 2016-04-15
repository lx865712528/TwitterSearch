package com.suneony.demo;

import com.suneony.twitter.search.SearchFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by liuxiao on 2016/4/15 0015.
 */
public class GetTweet {


    public static String getStr(Integer dd) {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse(dd.toString());
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            return "2011-01-01";
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SearchFactory sf = new SearchFactory();
        Integer start = 20110101;
        Integer end = 20120101;
        int cc = 0;
        while (start < end) {
            ArrayList<String> tweetListOfKeyword = (ArrayList<String>) sf.search("\"paper accepted since:" + getStr(start) + " until:" + getStr(start + 1) + "\"");
            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream("data/" + start + ".json"));
            for (String string : tweetListOfKeyword) {
                outputStream.write(string + "\n");
            }
            System.out.print(start + "done!");
            ++cc;
            outputStream.close();
            if (cc == 160) {
                cc = 0;
                Thread.sleep(900000);
            }
            ++start;
        }
        System.out.println("Done!");
    }
}
