package com.example.twiitergui;
//BASIC BODY OF TWEET
public class NodeTweet {
    String userhandle;
    String name;
    String tweet;

    NodeTweet nexttweet;


    public NodeTweet(String userhandle, String name, String tweet) {
        this.userhandle = userhandle;
        this.name = name;
        this.tweet = tweet;
        this.nexttweet = null;
    }
}
