package com.example.twiitergui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Notification {
   private Stack<String> list=new Stack<>();
    public void readnotifications(String username) throws FileNotFoundException {
        //READ & PROCESS NOTIFICATIONS FROM THE FILE

        Scanner s=new Scanner(new File("src/followers.txt"));
        while (s.hasNextLine()) {
            String handle=s.nextLine();
            String follower_handle=s.nextLine();
            if(handle.equals(username)){
                 list.push(follower_handle+" Followed you.");
            }
        }
    }
    public ObservableList return_list(){
        //CONVERT STACK TO AN ARRAYLIST

       ObservableList<String> notifications= FXCollections.observableArrayList();
       while (!list.empty()){
           notifications.add(list.pop());
       }
       return notifications;
    }
}
