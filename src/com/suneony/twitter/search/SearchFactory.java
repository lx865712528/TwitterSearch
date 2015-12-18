package com.suneony.twitter.search;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.json.DataObjectFactory;

public class SearchFactory {
    private Twitter twitter = null;

    public SearchFactory() {
        TwitterInstance twitterInstance = new TwitterInstance();
        this.twitter = twitterInstance.instance();
    }

    /**
     * @param keyword
     *            检索的关键词
     * @return 返回15条相关博文
     */
    @SuppressWarnings("unused")
    public List<String> search(String keyword) {
        // 根据关键词构建Twitter查询
        Query query = new Query(keyword);
        // 查询结果
        QueryResult result = null;
        // 函数返回结果
        ArrayList<String> statusStringList = new ArrayList<String>();
        try {
            // 检索
            result = this.twitter.search(query);
            // 获取检索到的Tweet集合
            List<Status> statusList = result.getTweets();
            // 将Status转为标准JSON格式，方便解析
            for (Status status: statusList) {
                String string = DataObjectFactory.getRawJSON(status);
                statusStringList.add(string);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            return null;
        }
        return statusStringList;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SearchFactory sf = new SearchFactory();
        BufferedReader inputStream = new BufferedReader(
                new FileReader("data/bdstechEntity.txt"));
        int counter = 0, cc=0;
        while (true) {
            String keyword = inputStream.readLine();
            if (keyword == null || keyword.length() == 0)
                break;
            ++counter;
            ArrayList<String> tweetListOfKeyword = (ArrayList<String>) sf
                    .search("\""+keyword+"\"");
            ++cc;
            OutputStreamWriter outputStream = new OutputStreamWriter(
                    new FileOutputStream("data/" + counter + ".json"));
            for (String string: tweetListOfKeyword) {
                outputStream.write(string + "\n");
            }
            outputStream.close();
            System.out.println(keyword + "  " + counter + " done.");
            if(cc==160) {
                cc=0;
                Thread.sleep(900000);
            }
        }
        inputStream.close();
    }
}
