package com.example.twiitergui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Scanner;

public class Tweet {
          NodeTweet front;
          NodeTweet rear;
          public void postTweet(String name,String handle,String tweet) throws IOException {
              //ADD TWEET TO QUEUE AND ALSO WRITE TO FILE

              BufferedWriter bw=new BufferedWriter(new FileWriter(new File("src/tweets.txt"),true));
              bw.write(name);
              bw.newLine();
              bw.write(handle);
              bw.newLine();
              bw.write(tweet);
              bw.newLine();
              bw.close();
              if(front==null && rear==null){
                  front=new NodeTweet(handle,name,tweet);
                  rear=front;
                  return;
              }
              NodeTweet nodeTweet=new NodeTweet(handle,name,tweet);
              rear.nexttweet=nodeTweet;
              rear=nodeTweet;

          }


    public void print(){
              NodeTweet ptr=front;
              while (ptr!=null){
                  System.out.println(ptr.tweet);
                  ptr=ptr.nexttweet;
              }
    }

    public boolean display_tweets(Label name, Label handle, Label tweet){
              //DISPLAY FUNCTION USED TO SET DATA ON LABELS ALL WORKS AS DEQUEUE FUNCTION

        if(front==null) {
            name.setText("");
            handle.setText("");
            tweet.setText("");

            return true;
        }
        name.setText("  "+front.name);
        handle.setText("  @"+front.userhandle);
        tweet.setText(" "+front.tweet);
        front=front.nexttweet;
        return false;
    }

    public void readtweet() throws FileNotFoundException {
              //READ ALL THE TWEETS FROM THE FILE AND ADD TO QUEUE
              Scanner s=new Scanner(new File("src/tweets.txt"));
              while (s.hasNextLine()){
                  String name= s.nextLine();
                  String handle= s.nextLine();
                  String tweet=s.nextLine();
                  if(front==null && rear==null){
                      front=new NodeTweet(handle,name,tweet);
                      rear=front;
                  }else {
                      NodeTweet nodeTweet=new NodeTweet(handle,name,tweet);
                      rear.nexttweet=nodeTweet;
                      rear=nodeTweet;
                  }
              }
          }

}
