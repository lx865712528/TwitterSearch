package com.suneony.twitter.search;

import twitter4j.*;
import twitter4j.json.DataObjectFactory;

import java.util.ArrayList;
import java.util.List;

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
}
