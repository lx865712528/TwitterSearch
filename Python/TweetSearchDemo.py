from TwitterSearch import *

try:
    tso = TwitterSearchOrder() # create a TwitterSearchOrder object
    #tso.set_max_id(686169053594021888)
    tso.set_since_id(686169053594021888)
    #tso.set_search_url("https://twitter.com/blakestacey/status/686239594820538369")
    tso.set_keywords(["YouTuber"]) # let's define all words we would like to have a look for
    print tso.create_search_url()
    #tso.set_language('en') # we want to see English tweets only

    # it's about time to create a TwitterSearch object with our secret tokens
    ts = TwitterSearch(
        consumer_key = 'Epz4oaR0Z7OZ0qYrUO2ENYIW7',
        consumer_secret = 'wZGZQsNXsVZFBDDzqR50jZSgMqESdWpt9r5YApUkW13rBZ0iFo',
        access_token = '709035290-RLltll3si2PXDW7eZyfgImHDEpQNXtYUEPYCVgBc',
        access_token_secret = 'KQxvwmP1pnGTLViPnDJHw5wSBP3Ain3RG7FyA0Ac7TIuD'
     )

     # this is where the fun actually starts :)
    for tweet in ts.search_tweets_iterable(tso):
        print(tweet)

except TwitterSearchException as e: # take care of all those ugly errors if there are some
    print(e)