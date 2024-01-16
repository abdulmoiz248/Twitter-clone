package com.example.twiitergui;

import java.io.*;
import java.util.Scanner;

public class Users {
    NodeUser user;

    public void adduser(String name,String password,String handle) throws IOException {
        // REIGSTER/ADDS A USER AND ALSO STORES ITS DATA IN THE FILE
        NodeUser newuser=new NodeUser(name,password,handle);
        BufferedWriter bw=new BufferedWriter(new FileWriter(new File("src/userRecord.txt"),true));
        bw.write(name);
        bw.newLine();
        bw.write(handle);
        bw.newLine();
        bw.write(password);
        bw.newLine();
        bw.close();
        if(user==null){
            user=newuser;
            return;
        }
        newuser.nextuser=user;
        user=newuser;

    }

    public Users() throws FileNotFoundException {
        readusers();
    }
    public void print(){
        NodeUser ptr=user;
        while (ptr!=null){
            System.out.print(ptr.name+" -> ");
            ptr=ptr.nextuser;
        }
    }

public NodeUser searchuser(String handle){
        //SEARCH THE USER IN THE LINKED LIST
    NodeUser ptr=user;
    while (ptr!=null){
       if(ptr.handle.equals(handle))
           return ptr;
       ptr=ptr.nextuser;
    }
    return null;
}
    private void readusers() throws FileNotFoundException {
        //READS USERS FROM THE FILE
        Scanner s=new Scanner(new File("src/userRecord.txt"));
        while (s.hasNextLine()){
           String name= s.nextLine();
           String handle= s.nextLine();
           String password=s.nextLine();
           if(name!=null){
               NodeUser newuser=new NodeUser(name,password,handle);
               read_followers(handle,newuser);
               read_following(handle,newuser);
               if(user==null){
                   user=newuser;

               }else {
                   newuser.nextuser=user;
                   user=newuser;
               }

           }

        }
    }
public void read_followers(String name, NodeUser newuser) throws FileNotFoundException {
    //READ ALL THE FOLLOWERS OF A USER FROM FILE
    Scanner s=new Scanner(new File("src/followers.txt"));
    while (s.hasNextLine()) {
        String handle=s.nextLine();
        String follower_handle=s.nextLine();
        if(handle.equals(name)){
            newuser.followers.add(follower_handle);
            newuser.follower_count++;
        }
    }
}
    public void read_following(String name, NodeUser newuser) throws FileNotFoundException {
        //READ FOLLOWING OF USER FROM FILE
        Scanner s=new Scanner(new File("src/following.txt"));
        while (s.hasNextLine()){
            String handle=s.nextLine();
            String following_handle=s.nextLine();
            if(handle.equals(name)){
                newuser.following.add(following_handle);
                newuser.follwing_count++;
            }
        }
    }
}
