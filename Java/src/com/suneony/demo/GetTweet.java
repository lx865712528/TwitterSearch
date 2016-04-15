package com.suneony.demo;

import com.suneony.twitter.search.SearchFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by liuxiao on 2016/4/15 0015.
 */
public class GetTweet {

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        SearchFactory sf = new SearchFactory();
        Date start = new SimpleDateFormat("yyyyMMdd").parse("20110101");
        Date end = new SimpleDateFormat("yyyyMMdd").parse("20120101");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(start);
        int cc = 0;
        while (start.getTime() < end.getTime()) {
            String fileName = new SimpleDateFormat("yyyyMMdd").format(start) + ".json";
            String keyword = "paper accepted since:" + new SimpleDateFormat("yyyy-MM-dd").format(start) + " until:";
            calendar.add(calendar.DATE, 1);
            start = calendar.getTime();
            keyword += new SimpleDateFormat("yyyy-MM-dd").format(start);
            ArrayList<String> tweetListOfKeyword = (ArrayList<String>) sf.search("\"" + keyword + "\"");
            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream("data/" + fileName));
            for (String string : tweetListOfKeyword) outputStream.write(string + "\n");
            System.out.print(start + "done!");
            ++cc;
            outputStream.close();
            if (cc == 160) {
                cc = 0;
                Thread.sleep(900000);
            }
        }
        System.out.println("Done!");
    }
}
