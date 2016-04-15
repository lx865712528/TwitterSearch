# -*- coding: utf-8 -*-

import scrapy
from TwitterSearch import *

class twitterSpider(scrapy.Spider):
    name = 'twitterSpider'
    start_urls = ['https://twitter.com',]

    def parse(self, response):
        try:
            tso = TwitterSearchOrder()  # create a TwitterSearchOrder object
            tso.set_keywords(['paper accepted start:2011-01-01 until:2012-01-01'])  # let's define all words we would like to have a look for
            tso.set_language('en')  # we want to see English tweets only

            # it's about time to create a TwitterSearch object with our secret tokens
            ts = TwitterSearch(
                consumer_key='W4bA51GZCWnXraw5r5LMLn0wy',
                consumer_secret='pPIlYhNZ2rDR0IlZC2kkaljKuq9o6I9pDp0X8L6ZPXxt6FsWDC',
                access_token='3645009020-X646e4MkGGQuD8abAxbOHeR4UJCXclx7RkeOTo9',
                access_token_secret='Cr5WOQAqBlAgETE4suj8xVo5Pm5KrYW41Gq5iLpV45zh6'
            )

            # this is where the fun actually starts :)
            for tweet in ts.search_tweets_iterable(tso):
                yield tweet

        except TwitterSearchException as e:  # take care of all those ugly errors if there are some
            print(e)
