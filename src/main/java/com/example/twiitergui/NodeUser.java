package com.example.twiitergui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeUser {
    String name;
    String handle;
    String password;
    ObservableList<String> following= FXCollections.observableArrayList();   //ARRAYLIST
    ObservableList<String> followers= FXCollections.observableArrayList();   //ARRAYLIST
    Notification notifications=new Notification(); //STACK
    Tweet usertweets=new Tweet();  //QUEUE
    int follower_count=0;
    int follwing_count=0;
    NodeUser nextuser;
    Tweet read_tweets_for_user=new Tweet(); //QUEUE


    public void setUsertweets() throws FileNotFoundException {
        //THIS FUNCTION LOAD TWEETS POSTED BY THE USER AND ADD TO THE QUEUE WITH THE HELP OF A HELPER FUNCTION

        usertweets.front=null;
        usertweets.rear=null;
        Tweet t=new Tweet();
        t.readtweet();
        NodeTweet ptr=t.front;
        while (ptr!=null){
            if(this.handle.equals(ptr.userhandle)){
                add_tweet_of_user(ptr.userhandle, ptr.name, ptr.tweet);
            }
            ptr=ptr.nexttweet;
        }
    }
    private void add_tweet_of_user(String handle,String name ,String tweet){
        //HELPER FUNCTION (ADD TWEETS TO THE QUEUE)

        if(usertweets.front==null && usertweets.rear==null){
            usertweets.front=new NodeTweet(handle,name,tweet);
            usertweets.rear=usertweets.front;
            return;
        }
        NodeTweet nodeTweet=new NodeTweet(handle,name,tweet);
        usertweets.rear.nexttweet=nodeTweet;
        usertweets.rear=nodeTweet;
    }
    public void read_tweets() throws FileNotFoundException {
        //READ TWEETS TO SHOW USER BASED ON FOLLOWING AND ADD TO THE QUEUE
        Tweet t=new Tweet();
        t.readtweet();
        NodeTweet ptr=t.front;
        while (ptr!=null){
            if(verify_following(ptr.userhandle)){
                add_tweet(ptr.userhandle, ptr.name, ptr.tweet);
            }
            ptr=ptr.nexttweet;
        }
    }
    private void add_tweet(String handle,String name ,String tweet){
        //HELPER FUNCTION TO ADD TWEETS TO QUEUE
        if(read_tweets_for_user.front==null && read_tweets_for_user.rear==null){
            read_tweets_for_user.front=new NodeTweet(handle,name,tweet);
            read_tweets_for_user.rear=read_tweets_for_user.front;
            return;
        }
        NodeTweet nodeTweet=new NodeTweet(handle,name,tweet);
        read_tweets_for_user.rear.nexttweet=nodeTweet;
        read_tweets_for_user.rear=nodeTweet;
    }

    public NodeUser(String name, String handle, String password) {
        this.name = name;
        this.password = password;
        this.handle=handle;
        this.nextuser = null;
    }
    private void add_follow_tweets(String handle) throws FileNotFoundException {
        //ADD TWEETS TO QUEUE WHEN FOLLOW SOMEONE
        Tweet t=new Tweet();
        t.readtweet();
        NodeTweet ptr=t.front;
        while (ptr!=null){
            if(handle.equals(ptr.userhandle)){
             add_tweet(ptr.userhandle,ptr.name, ptr.tweet);
            }
            ptr=ptr.nexttweet;
        }
    }
    public void addfollower(String handle) throws IOException {
        //VERIFY AND ADD FOLLOWER
        if(this.handle!=handle)
       if(!verify_follower(handle)) {
            followers.add(handle);
            follower_count++;
           if(check_in_file(handle,new File("src/followers.txt"))){
               write_to_file(handle,new File("src/followers.txt"));
           }
        }
    }
    private boolean verify_follower(String handle){
        //HELPER FUNCTION
        for(String f:followers){
            if(f.equals(handle)) return true;
        }
        return false;
    }
    private boolean verify_following(String handle){
        //HELPER FUNCTION
        for(String f:following){
            if(f.equals(handle)) return true;
        }
        return false;
    }
    public void addfollowing(String handle) throws IOException {
        //VERIFY AND ADD FOLLOWING
        if(this.handle!=handle)
       if(!verify_following(handle)) {
            following.add(handle);
            follwing_count++;
           add_follow_tweets(handle);
            if(check_in_file(handle,new File("src/following.txt"))){
                write_to_file(handle,new File("src/following.txt"));
            }
        }
    }

    private void write_to_file(String handle,File f) throws IOException {
        //FILE HANDLING
        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
        bw.write(this.handle);
        bw.newLine();
        bw.write(handle);
        bw.newLine();
        bw.close();

    }
    private boolean check_in_file(String handle,File f) throws FileNotFoundException {
        //VERIFY  FOLLOWER OR FOLLOWING IN THE FILE
        Scanner s=new Scanner(f);
        while (s.hasNextLine()){
            String userhandle=s.nextLine();
            String temp=s.nextLine();
          if(userhandle.equals(this.handle))
            if(temp.equals(handle))
            {
             return false;
            }
        }
        return true;
    }

    public void remove_follower(String handle, Users users) throws Exception { //handle=user needed to remove
        //REMOVE A FOLLOWER
        for(String f:followers)
        {
            if(f.equals(handle))
            {
                followers.remove(handle);
                follower_count--;
               NodeUser temp= users.searchuser(handle);
               temp.remove_following(this.handle,users);
                Scanner s=new Scanner(new File("src/followers.txt"));
                StringBuilder data=new StringBuilder();
                while (s.hasNextLine()){
                    String userhandle=s.nextLine(); //ye bnda
                    String handle_curr=s.nextLine(); //iska follower ye ha
                    if(!(userhandle.equals(this.handle) && handle_curr.equals(handle)))
                        {
                            data.append(userhandle).append("\n");
                            data.append(handle_curr).append("\n");
                        }
                }
                s.close();
                FileWriter writer = new FileWriter(new File("src/followers.txt"));
                writer.write(data.toString());
                writer.close();
                return;
            }
        }

    }

   private void removeTweets(String handle) {
        //REMOVE TWEETS OF USER FROM QUEUE AFTER UNFOLLOWING
       NodeTweet current = read_tweets_for_user.front;
       NodeTweet previous = null;

       while (current != null) {
           if (current.userhandle.equals(handle)) {
               // Remove the current node

               // Update the linked list references
               if (previous != null) {
                   // If it's not the first node
                   previous.nexttweet = current.nexttweet;
               } else {
                   // If it's the first node, update the front reference
                   read_tweets_for_user.front = current.nexttweet;
               }

               // Move to the next node without advancing the previous pointer
               current = current.nexttweet;
           } else {
               // Move to the next node, updating the previous pointer
               previous = current;
               current = current.nexttweet;
           }
       }
   }

    public void remove_following(String handle, Users users) throws Exception {
        //REMOVE A USER FROM FOLLOWING
        for(String f:following)
        {
            if(f.equals(handle))
            {
                following.remove(handle);
                NodeUser temp=users.searchuser(handle);
                temp.remove_follower(this.handle,users);
                follwing_count--;
                removeTweets(handle);
                Scanner s=new Scanner(new File("src/following.txt"));
                StringBuilder data=new StringBuilder();
                while (s.hasNextLine()){
                    String userhandle=s.nextLine(); //ye bnda
                    String handle_curr=s.nextLine(); //isko follow krta ha

                    if(!(userhandle.equals(this.handle) && handle_curr.equals(handle)))
                        {
                            data.append(userhandle).append("\n");
                            data.append(handle_curr).append("\n");
                        }
                }
                s.close();
                FileWriter writer = new FileWriter(new File("src/following.txt"));
                writer.write(data.toString());
                writer.close();
                return;
            }
        }

    }


}
