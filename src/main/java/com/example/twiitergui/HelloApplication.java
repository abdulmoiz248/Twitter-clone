package com.example.twiitergui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //declaration and initialization
        Users users=new Users();  //LinkedList (Contains data of all users)
        final NodeUser[] current_user = new NodeUser[1];  //Active user
        Scene s[]=new Scene[10];
        GridPane g[]=new GridPane[10];
        stage.setTitle("Twitter");
        Label l[]=new Label[70];
        for(int i=0;i<g.length;i++){
            g[i]=new GridPane();
            s[i]=new Scene(g[i],500,692);
            g[i].setVgap(10);
            g[i].setHgap(10);
            g[i].setAlignment(Pos.CENTER);
            g[i].setBackground(Background.fill(Color.web("08203e")));
        }
        for(int i=0;i<l.length; i++) {
            l[i] = new Label();
        }

         stage.setScene(s[0]);
//Font setting , Buttons for G0
        l[0]=new Label("Twitter \uD83E\uDD85");
        l[0].setStyle("-fx-font-weight: bold; -fx-font-size: 24pt; -fx-text-fill: a4c6b8;");
        PasswordField password=new PasswordField();
        TextField name=new TextField();
        l[1]=new Label("UserName: ");
        l[1].setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;");
        l[2]=new Label("Password: ");
        l[2].setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;");
        Button login=new Button("   Login   ");
        login.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        l[8].setStyle("-fx-text-fill: red;");
        Button signup=new Button("  Sign Up?  ");
        signup.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        HBox hBox=new HBox(login,signup);
        g[0].add(l[1],0,3);
        g[0].add(name,1,3);
        g[0].add(l[2],0,4);
        g[0].add(password,1,4);
        g[0].add(hBox,1,5);
        g[0].add(l[8],1,6);
        g[0].add(l[0],1,0);
//Font setting, Buttons G1
       l[3]=new Label("Name: ");
       l[3].setStyle("-fx-font-weight: bold; -fx-text-fill: #a4c6b8;");
       l[4]=new Label("UserName: ");
       l[4].setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;");
       l[5]=new Label("Password: ");
       l[5].setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;");
       TextField namest=new TextField();
       TextField usernamest=new TextField();
       PasswordField passst=new PasswordField();
         l[6]=new Label(" ");
        l[7]=new Label("Sign Up to Twitter");
       l[7].setStyle("-fx-font-weight: bold;-fx-font-size: 16pt; -fx-text-fill: a4c6b8;");
       Button signin=new Button("      Sign In     ");
       signin.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color:#a4c6b8");
       Button back_to_0=new Button("  Back  ");
       back_to_0.setStyle("-fx-font-weight: bold;-fx-text-fill:a4c6b8 ;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");

       l[6].setStyle("-fx-text-fill: red;");

       HBox hbox1=new HBox(signin,back_to_0);
       g[1].add(l[7],1,2);
        g[1].add(l[3],0,3);
        g[1].add(namest,1,3);
        g[1].add(l[4],0,4);
        g[1].add(usernamest,1,4);
        g[1].add(l[5],0,5);
        g[1].add(passst,1,5);
        g[1].add(l[6],1,7);
        g[1].add(hbox1,1,6);
        l[9].setStyle("-fx-text-fill: #bc1b68;fx-font-weight: bold;");
        l[11].setStyle("-fx-text-fill: #bc1b68;fx-font-weight: bold;");
        l[10].setStyle("-fx-text-fill: #a4c6b8;fx-font-weight: bold;");
        l[12].setStyle("-fx-text-fill: #a4c6b8;fx-font-weight: bold;");
//G2
        VBox follower=new VBox(l[9],l[10]);
        VBox following=new VBox(l[11],l[12]);
        Button search=new Button("\uD83D\uDD0E");
        search.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color:#a4c6b8");
        Button logout=new Button("Logout");
        logout.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        Button follow=new Button("Follow");
        follow.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        Button remove_follow=new Button("Unfollow");
        remove_follow.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        Button post_tweet=new Button("Post Tweet");
        post_tweet.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        Button profile=new Button("\uD83D\uDC64");
        Button home=new Button("\uD83C\uDFE0");
        Button headername=new Button();
        home.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        profile.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        headername.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-font-size: 16pt;-fx-background-color: #08203e;-fx-border-color: #08203e");
        HBox header1=new HBox(new Label("   "),follower,new Label("   "),following,new Label("                 "),search,profile,home,post_tweet,logout);
        Line vl=new Line();
        vl.setStartX(0);
        vl.setEndX(500);
        vl.setStroke(Color.web("#a4c6b8"));
        VBox header =new VBox(headername,header1,new Label(" "),vl);
        header1.setSpacing(10);
        g[2].setAlignment(Pos.TOP_CENTER);
        g[2].add(header,0,0);
        g[2].setHgap(2);

        Rectangle r1=new Rectangle(500,100, Color.web("557c93"));
        Rectangle r2=new Rectangle(500,100,Color.web("557c93") );
        Rectangle r3=new Rectangle(500,100, Color.web("557c93"));
        Rectangle r4=new Rectangle(500,100,  Color.web("557c93"));
        Rectangle r5=new Rectangle(500,100,  Color.web("557c93"));

        r1.setStroke(Color.web("#a4c6b8"));
    //    r1.setStrokeWidth(3);
        r2.setStroke(Color.web("#a4c6b8"));
      //  r2.setStrokeWidth(3);
        r3.setStroke(Color.web("#a4c6b8"));
      //  r3.setStrokeWidth(3);
        r4.setStroke(Color.web("#a4c6b8"));
      //  r4.setStrokeWidth(3);
        r5.setStroke(Color.web("#a4c6b8"));


        g[2].add(r1,0,1);
        g[2].add(r2,0,2);
        g[2].add(r3,0,3);
        g[2].add(r4,0,4);
        g[2].add(r5,0,5);
        Button like[]=new Button[5];
        for(int i=0;i< like.length;i++) {
            like[i] = new Button("❤");
            like[i].setStyle("-fx-text-fill: #a4c6b8; -fx-border-color:  #08203e;");
        }

        Button next=new Button("⬇");
        next.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        next.setMinWidth(500);
        l[14].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #a4c6b8;");
        l[15].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
        l[16].setStyle("-fx-font-size: 10pt;-fx-text-fill: #08203e;");
         VBox vBox1=new VBox();

        l[17].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #a4c6b8;");
        l[18].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
        l[19].setStyle("-fx-font-size: 10pt;-fx-text-fill: #08203e;");
       VBox vBox2=new VBox();
        l[20].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #a4c6b8;");
        l[21].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
        l[22].setStyle("-fx-font-size: 10pt;-fx-text-fill: #08203e;");
       VBox vBox3=new VBox();
        l[23].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #a4c6b8;");
        l[24].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
        l[25].setStyle("-fx-font-size: 10pt;-fx-text-fill: #08203e;");
        VBox vBox4=new VBox();
        l[26].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #a4c6b8;");
        l[27].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
        l[28].setStyle("-fx-font-size: 10pt;-fx-text-fill: #08203e;");
        VBox vBox5=new VBox(l[26],l[27],l[28],like[4]);
        HBox paction=new HBox(follow,new Label(" "),remove_follow);
        g[2].add(vBox1,0,1);
        g[2].add(vBox2,0,2);
        g[2].add(vBox3,0,3);
        g[2].add(vBox4,0,4);
        g[2].add(vBox5,0,5);
         g[2].add(next,0,6);

 //G3
        l[29].setText("UserName: ");
        l[29].setStyle("-fx-font-weight: bold; -fx-text-fill: #a4c6b8;");
        TextField searchname=new TextField();
        g[3].add(l[30],0,2);
        l[30].setStyle("-fx-text-fill: red;");
        Button searchs3=new Button("Search");
        searchs3.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        g[3].setAlignment(Pos.TOP_CENTER);
        HBox h=new HBox(l[29],new Label(" "),searchname,new Label(" "),searchs3);
        g[3].add(h,0,1);
        g[3].add(l[31],0,3);
        l[31].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill:#a4c6b8 ;");

        g[3].add(l[32],0,4);
        l[32].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt; -fx-text-fill: orange;");

        g[3].add(l[33],0,5);
        l[33].setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;");

        g[3].add(l[34],0,6);
        l[34].setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;");
//G4
        g[4].setAlignment(Pos.TOP_CENTER);
        l[35].setStyle("-fx-font-weight: bold;-fx-font-size: 20pt; -fx-text-fill: #a4c6b8;");
        l[36].setStyle("-fx-font-weight: bold;-fx-font-size: 14pt; -fx-text-fill: orange;");
        Button my_followers=new Button();
        Button my_following=new Button();
        HBox profile_actions=new HBox(new Label(""),my_followers,new Label("  "),my_following);
        my_following.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt; -fx-text-fill: #a4c6b8;-fx-border-color: #08203e;-fx-background-color: #08203e");
        my_followers.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt; -fx-text-fill: #a4c6b8;-fx-border-color: #08203e;-fx-background-color: #08203e");
        g[4].add(l[35],0,1);
        g[4].add(l[36],0,2);
        g[4].add(profile_actions,0,2);
        Rectangle sr1=new Rectangle(500,100, Color.web("557c93"));
        Rectangle sr2=new Rectangle(500,100,Color.web("557c93"));
        Rectangle sr3=new Rectangle(500,100,  Color.web("557c93"));
//G5
        Stack tweetrecord=new Stack<>();
        Stack tempstack=new Stack<>();
        Button undo=new Button("↩");
        undo.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        Button redo=new Button("↪");
        redo.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        Button post=new Button("Post");
        post.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
        TextField tweet=new TextField();
        g[5].add(tweet,0,2);
        HBox postheader=new HBox(new Label(" "),post,new Label("                                                        " +
                "                                                               "),undo,new Label("  "),redo);
        g[5].add(postheader,0,5);
        final int[] wordcount = {0};
        Label counter=new Label(wordcount[0]+"/100");
        counter.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e;");
        g[5].add(counter,0,6);
        //SET ON ACTIONS

        post.setOnAction(new EventHandler<ActionEvent>() {   //POST THE TWEET
            @Override
            public void handle(ActionEvent actionEvent) {
                if(wordcount[0]<101){      //VALIDATE WORD COUNT
                    wordcount[0]=0;
                    counter.setText("0/100");
                while (!tweetrecord.isEmpty()){     //EMPTY STACKS
                    tweetrecord.pop();
                }
                while (!tempstack.isEmpty())
                    tempstack.pop();

                try { //STORE DATA TO FILE AND ADD TO RECORD
                    current_user[0].usertweets.postTweet(current_user[0].name,current_user[0].handle,tweet.getText());
                    tweet.setText("");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }}
        });

       undo.setOnAction(new EventHandler<ActionEvent>() { //UNDO BUTTON
           @Override
           public void handle(ActionEvent actionEvent) {
               try {
                   wordcount[0]--;
                   if(wordcount[0]>100){
                       counter.setStyle("-fx-font-weight: bold;-fx-text-fill: red;-fx-background-color: #08203e;");
                   }else counter.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e;");
                   counter.setText(wordcount[0]+"/100");
               tempstack.push(tweetrecord.pop());
               String currentText = tweet.getText();
               if (!currentText.isEmpty()) {
                   String newText = currentText.substring(0, currentText.length() - 1);
                   tweet.setText(newText);
               }
           }catch (Exception e){

               }
           }
       });
       redo.setOnAction(new EventHandler<ActionEvent>() { //REDO
           @Override
           public void handle(ActionEvent actionEvent) {
               try {
                   String s = (String) tempstack.pop();
                   tweetrecord.push(s);
                   String current = tweet.getText() + s;
                   tweet.setText(current);
                   wordcount[0]++;
                   if(wordcount[0]>100){
                       counter.setStyle("-fx-font-weight: bold;-fx-text-fill: red;-fx-background-color: #08203e;");
                   }else counter.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e;");
                   counter.setText(wordcount[0]+"/100");
               }catch (Exception e){

               }
           }
       });

        post_tweet.setOnAction(new EventHandler<ActionEvent>() {  //OPEN POST TWEET SCREEN
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    wordcount[0]=0;
                    g[3].getChildren().remove(header);
                    g[4].getChildren().remove(header);
                    g[2].getChildren().remove(header);
                    g[5].add(header,0,0);
                    l[30].setText("");
                    l[31].setText("");
                    l[32].setText("");
                    l[33].setText("");
                    l[34].setText("");
                    wordcount[0]=0;
                    g[3].getChildren().removeAll(paction,sr1,sr2,sr3,l[50],l[51],l[52],l[53],l[54],l[55],l[56],l[57],l[58]);
                    stage.setScene(s[5]);
                }catch (Exception e){

                }
                g[5].setAlignment(Pos.TOP_CENTER);
                tweet.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt; -fx-text-fill: #08203e;-fx-border-color: #08203e;-fx-background-color: #a4c6b8");
                tweet.setPromptText("Write tweet..!! ");
                tweet.setAlignment(Pos.TOP_LEFT);
                tweet.setPrefWidth(480);
                tweet.setPrefHeight(200);
                final boolean[] check = {true};
                tweet.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode().equals(javafx.scene.input.KeyCode.BACK_SPACE)) {
                       if(wordcount[0]!=0)
                               wordcount[0]--;
                        System.out.println("Pressed Back space");
                        if(wordcount[0]>100){
                            counter.setStyle("-fx-font-weight: bold;-fx-text-fill: red;-fx-background-color: #08203e;");
                        }else counter.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e;");
                       counter.setText(wordcount[0]+"/100");
                        check[0] =false;
                    }
                });
                tweet.addEventFilter(KeyEvent.KEY_TYPED, event -> {
                    if (!event.getCharacter().isEmpty()) {
                        if(check[0]) {
                            try {
                                tempstack.pop();
                            }catch (Exception e){

                            }
                            tweetrecord.push(event.getCharacter());
                            wordcount[0]++;
                            if(wordcount[0]>100){
                                counter.setStyle("-fx-font-weight: bold;-fx-text-fill: red;-fx-background-color: #08203e;");
                            }else counter.setStyle("-fx-font-weight: bold;-fx-text-fill: #a4c6b8;-fx-background-color: #08203e;");
                            counter.setText(wordcount[0]+"/100");
                        }else {
                            check[0]=true;
                           try {
                                tempstack.push(tweetrecord.pop());
                            }catch(Exception e){

                           }
                        }
                    }
                });

            }
        });

       headername.setOnAction(new EventHandler<ActionEvent>() { //OPENS NOTIFICATION
           @Override
           public void handle(ActionEvent actionEvent) {
               try {
                   current_user[0].notifications.readnotifications(current_user[0].handle);
               } catch (FileNotFoundException e) {
                   throw new RuntimeException(e);
               }
               try {
                   g[3].getChildren().remove(header);
                   g[4].getChildren().remove(header);
                   g[2].getChildren().remove(header);
                   g[2].add(header,0,0);
                   l[30].setText("");
                   l[31].setText("");
                   l[32].setText("");
                   l[33].setText("");
                   l[34].setText("");
                   g[3].getChildren().removeAll(paction,sr1,sr2,sr3,l[50],l[51],l[52],l[53],l[54],l[55],l[56],l[57],l[58]);
               }catch (Exception e){

               }
               Button back = new Button("Back <-");
               back.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
               back.setOnAction(e -> stage.setScene(s[2]));
               TableView<String> tableView = new TableView<>();
               tableView.setPrefWidth(300);
               TableColumn<String, String> column = new TableColumn<>("Notifications");
               column.setPrefWidth(300);
               column.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e ;");
               column.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue()));
               tableView.getColumns().add(column);
               tableView.setItems(current_user[0].notifications.return_list());
               stage.setScene(s[8]);
               g[8].add(tableView,0,0);
               g[8].add(back,0,1);

           }
       });

        profile.setOnAction(new EventHandler<ActionEvent>() { //OPEN PROFILE SCENE
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    g[3].getChildren().remove(header);
                    g[2].getChildren().remove(header);
                    g[5].getChildren().remove(header);
                    g[4].add(header,0,0);
                    l[30].setText("");
                    l[31].setText("");
                    l[32].setText("");
                    l[33].setText("");
                    l[34].setText("");
                    g[3].getChildren().removeAll(paction,sr1,sr2,sr3,l[50],l[51],l[52],l[53],l[54],l[55],l[56],l[57],l[58]);

                }catch (IllegalArgumentException e){

                }
                stage.setScene(s[4]);
                    l[35].setText(" "+current_user[0].name);
                    l[36].setText(" @"+current_user[0].handle);

                    my_followers.setText(current_user[0].follower_count+" Followers");
                    my_following.setText(current_user[0].follwing_count+" Following");
                try {
                    current_user[0].setUsertweets();
                } catch (FileNotFoundException e) {

                }

                Rectangle r1=new Rectangle(500,100, Color.web("557c93"));
                Rectangle r2=new Rectangle(500,100,Color.web("557c93"));
                Rectangle r3=new Rectangle(500,100,  Color.web("557c93"));
                Rectangle r4=new Rectangle(500,100,  Color.web("557c93"));
                r1.setStroke(Color.web("#a4c6b8"));
                r2.setStroke(Color.web("#a4c6b8"));
                r3.setStroke(Color.web("#a4c6b8"));
                r4.setStroke(Color.web("#a4c6b8"));
                g[4].add(r1,0,4);
                g[4].add(r2,0,5);
                g[4].add(r3,0,6);
                g[4].add(r4,0,7);
                l[37].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                l[38].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                l[39].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");
                VBox vBox1=new VBox(l[37],l[38],l[39]);
                current_user[0].usertweets.display_tweets(l[37],l[38],l[39]);
                g[4].add(vBox1,0,4);
                l[40].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                l[41].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                l[42].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");
                VBox vBox2=new VBox(l[40],l[41],l[42]);
                current_user[0].usertweets.display_tweets(l[40],l[41],l[42]);
                g[4].add(vBox2,0,5);
                l[43].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                l[44].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                l[45].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");
                VBox vBox3=new VBox(l[43],l[44],l[45]);
                current_user[0].usertweets.display_tweets(l[43],l[44],l[45]);
                g[4].add(vBox3,0,6);
                l[46].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                l[47].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                l[48].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");
                VBox vBox4=new VBox(l[46],l[47],l[48]);
                current_user[0].usertweets.display_tweets(l[46],l[47],l[48]);
                g[4].add(vBox4,0,7);
            }
        });
        my_following.setOnAction(new EventHandler<ActionEvent>() {  //SHOWS MY FOLLOWING
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                stage.setScene(s[7]);
                Button back = new Button("Back <-");
                back.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
                back.setOnAction(e -> stage.setScene(s[4]));
                TableView<String> tableView = new TableView<>();
                tableView.setPrefWidth(300);
                TableColumn<String, String> column = new TableColumn<>("Following");
                column.setPrefWidth(300);
                column.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue()));
                tableView.getColumns().add(column);
                tableView.setItems(current_user[0].following);
                ComboBox<String> comboBox = new ComboBox<>(current_user[0].following);
                comboBox.setMinWidth(100);
                Button remove = new Button("Remove");
                remove.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
                comboBox.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill:#a4c6b8 ; -fx-border-color: #08203e");
                column.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e ;");

                    remove.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String user = comboBox.getValue();
                        try {
                            current_user[0].remove_following(user,users);
                            l[9].setText(String.valueOf("  " + current_user[0].follower_count + " "));
                            l[11].setText(String.valueOf("  " + current_user[0].follwing_count + " "));
                            my_following.setText(current_user[0].follwing_count + " Following");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                HBox removef = new HBox(comboBox, new Label("    "), remove,back);
                g[7].add(removef, 0, 3);
                g[7].add(tableView, 0, 2);
            }catch (Exception e){

                }
            }
        });
        my_followers.setOnAction(new EventHandler<ActionEvent>() {  //SHOWS MY FOLLOWERS
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    stage.setScene(s[6]);
                    Button back = new Button("Back <-");
                    back.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
                    back.setOnAction(e -> stage.setScene(s[4]));

                    TableView<String> tableView1 = new TableView<>();
                    tableView1.setPrefWidth(300);
                    TableColumn<String, String> column = new TableColumn<>("Followers");
                    column.setPrefWidth(300);
                    column.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue()));
                    tableView1.getColumns().add(column);
                    tableView1.setItems(current_user[0].followers);
                    ComboBox<String> comboBox = new ComboBox<>(current_user[0].followers);
                    comboBox.setMinWidth(100);
                    Button remove = new Button("Remove");
                    remove.setStyle("-fx-font-weight: bold;-fx-text-fill: a4c6b8;-fx-background-color: #08203e; -fx-border-color: #a4c6b8");
                    comboBox.setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill:#a4c6b8 ; -fx-border-color: #08203e");
                    column.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill:#08203e ;");
                    remove.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            String user = comboBox.getValue();
                            try {
                                current_user[0].remove_follower(user,users);
                                l[9].setText(String.valueOf("  " + current_user[0].follower_count + " "));
                                l[11].setText(String.valueOf("  " + current_user[0].follwing_count + " "));
                                my_followers.setText(current_user[0].follower_count + " Followers");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    HBox removeh = new HBox(comboBox, new Label("    "), remove,back);
                    g[6].add(removeh, 0, 3);
                    g[6].add(tableView1, 0, 2);
                }catch (Exception e){

                }
            }
        });

        login.setOnAction(new EventHandler<ActionEvent>() {     //VALIDATE AND LOGIN USER
            @Override
            public void handle(ActionEvent actionEvent) {
                String name1=name.getText();
                String pass=password.getText();
                if (!name1.isEmpty() && !pass.isEmpty()){
                    current_user[0] =verify_user(name1,pass,users);
                    if(current_user[0] ==null){
                        l[8].setText("Invalid UserName or Password");
                    }else {
                        stage.setScene(s[2]);
                        name.clear();
                        l[9].setText(String.valueOf("  "+current_user[0].follower_count+" "));
                        try {
                            current_user[0].read_tweets();
                        } catch (FileNotFoundException e) {
                            System.out.println(e);
                        }
                        try {
                            vBox1.getChildren().addAll(l[14],l[15],l[16],like[0]);
                            vBox2.getChildren().addAll(l[17],l[18],l[19],like[1]);
                            vBox3.getChildren().addAll(l[20],l[21],l[22],like[2]);
                            vBox4.getChildren().addAll(l[23],l[24],l[25],like[3]);
                            vBox5.getChildren().addAll(l[26],l[27],l[28],like[4]);
                        }catch (Exception e){

                        }

                        try {
                            boolean temp;
                            temp= current_user[0].read_tweets_for_user.display_tweets(l[14],l[15],l[16]);
                            if(temp) vBox1.getChildren().removeAll(l[14],l[15],l[16],like[0]);
                            temp= current_user[0].read_tweets_for_user.display_tweets(l[17],l[18],l[19]);
                            if(temp) vBox2.getChildren().removeAll(l[17],l[18],l[19],like[1]);
                            temp= current_user[0].read_tweets_for_user.display_tweets(l[20],l[21],l[22]);
                            if(temp) vBox3.getChildren().removeAll(l[20],l[21],l[22],like[2]);
                            temp= current_user[0].read_tweets_for_user.display_tweets(l[23],l[24],l[25]);
                            if(temp) vBox4.getChildren().removeAll(l[23],l[24],l[25],like[3]);
                            temp= current_user[0].read_tweets_for_user.display_tweets(l[26],l[27],l[28]);
                            if(temp)  vBox5.getChildren().removeAll(l[26],l[27],l[28],like[4]);
                        }catch (Exception e){

                        }
                        l[10].setText("Followers");
                        l[11].setText(String.valueOf("  "+current_user[0].follwing_count+" "));
                        l[12].setText("Following");
                        headername.setText("  "+current_user[0].name);
                        password.clear();
                        l[8].setText("");
                    }
                }else {
                    l[8].setText("Enter Valid Data..!!");
                }
            }
        });

        back_to_0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(s[0]);
                namest.clear();
                usernamest.clear();
                passst.clear();
                l[6].setText("");
            }
        });

        signin.setOnAction(new EventHandler<ActionEvent>() {   //VALIDATE AND SIGNIN/CREATE ID
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=namest.getText();
                String username=usernamest.getText();
                String pass=passst.getText();
                if(!username.isEmpty()){
                    if(verify_username(username,users)){
                        try {
                            if(!name.isEmpty() && !username.isEmpty() && !pass.isEmpty()) {
                                users.adduser(name, pass, username);
                                l[6].setText("");
                                namest.clear();
                                usernamest.clear();
                                passst.clear();
                                stage.setScene(s[0]);

                            }
                            else l[6].setText("Enter Valid Data..!!");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        l[6].setText("User Name Already Exist..!!");
                    }}else l[6].setText("Enter Valid Data..!!");
            }
        });

        search.setOnAction(new EventHandler<ActionEvent>() { //OPEN SEARCH SCREEN
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    l[30].setText("");
                    l[31].setText("");
                    l[32].setText("");
                    l[33].setText("");
                    l[34].setText("");
                    l[50].setText("");
                    l[51].setText("");
                    l[52].setText("");
                    l[53].setText("");
                    l[54].setText("");
                    l[55].setText("");
                    l[56].setText("");
                    l[57].setText("");
                    l[58].setText("");
                    g[2].getChildren().remove(header);
                    g[4].getChildren().remove(header);
                    g[5].getChildren().remove(header);
                    g[3].add(header,0,0);
                    searchname.setText("");
                    g[3].getChildren().removeAll(sr1,sr2,sr3, paction);
                }catch (IllegalArgumentException e){

                }
                stage.setScene(s[3]);
            }
        });

        home.setOnAction(new EventHandler<ActionEvent>() { //OPEN HOME SCREEN
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    g[3].getChildren().remove(header);
                    g[4].getChildren().remove(header);
                    g[5].getChildren().remove(header);
                    g[2].add(header,0,0);
                    l[30].setText("");
                    l[31].setText("");
                    l[32].setText("");
                    l[33].setText("");
                    l[34].setText("");
                    l[50].setText("");
                    l[51].setText("");
                    l[52].setText("");
                    l[53].setText("");
                    l[54].setText("");
                    l[55].setText("");
                    l[56].setText("");
                    l[57].setText("");
                    l[58].setText("");
                    g[3].getChildren().removeAll(paction,sr1,sr2,sr3,l[50],l[51],l[52],l[53],l[54],l[55],l[56],l[57],l[58]);
                }catch (IllegalArgumentException e){

                }
                stage.setScene(s[2]);

            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() { //LOGOUT USER
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    tweet.setText("");
                    l[30].setText("");
                    l[31].setText("");
                    l[32].setText("");
                    l[33].setText("");
                    l[34].setText("");
                    l[50].setText("");
                    l[51].setText("");
                    l[52].setText("");
                    l[53].setText("");
                    l[54].setText("");
                    l[55].setText("");
                    l[56].setText("");
                    l[57].setText("");
                    l[58].setText("");
                    g[3].getChildren().remove(header);
                    g[4].getChildren().remove(header);
                    g[5].getChildren().remove(header);
                    g[2].add(header,0,0);
                }catch (IllegalArgumentException e){

                }
                stage.setScene(s[0]);
                current_user[0]=null;
            }
        });

        searchs3.setOnAction(new EventHandler<ActionEvent>() {  //search the user
            @Override
            public void handle(ActionEvent actionEvent) {
                l[30].setText("");
                l[31].setText("");
                l[32].setText("");
                l[33].setText("");
                l[34].setText("");
                l[50].setText("");
                l[51].setText("");
                l[52].setText("");
                l[53].setText("");
                l[54].setText("");
                l[55].setText("");
                l[56].setText("");
                l[57].setText("");
                l[58].setText("");

               // g[3].getChildren().remove(follow);
                try {

                    g[3].getChildren().removeAll(paction,sr1,sr2,sr3,l[50],l[51],l[52],l[53],l[54],l[55],l[56],l[57],l[58]);
                }catch (Exception e){

                }

                String name=searchname.getText();
                if(!name.isEmpty()){
                    NodeUser temp=users.searchuser(name);
                    if(temp==null){
                        l[30].setText("User not Found");
                    } else if (temp.handle==current_user[0].handle) {
                        l[30].setText("User not Found");
                        try {
                            g[3].getChildren().remove(paction);
                        }catch (Exception e){

                        }
                    } else {

                        l[30].setText("");
                        l[31].setText("  " +temp.name);
                        l[32].setText("   @"+temp.handle);
                        l[33].setText("   Followers: "+temp.follower_count);
                        l[34].setText("   Following: "+temp.follwing_count);

                        l[50].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                        l[51].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                        l[52].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");

                        l[53].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                        l[54].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                        l[55].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");

                        l[56].setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;-fx-text-fill: #08203e;");
                        l[57].setStyle("-fx-font-weight: bold;-fx-font-size: 10pt;-fx-text-fill: orange;");
                        l[58].setStyle("-fx-font-size: 10pt;-fx-text-fill: #a4c6b8;");

                        try {
                            temp.setUsertweets();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        temp.usertweets.display_tweets(l[50],l[51],l[52]);
                        VBox vBoxs=new VBox(l[50],l[51],l[52]);
                        temp.usertweets.display_tweets(l[53],l[54],l[55]);
                        VBox vBoxs1=new VBox(l[53],l[54],l[55]);
                        temp.usertweets.display_tweets(l[56],l[57],l[58]);
                        VBox vBoxs2=new VBox(l[56],l[57],l[58]);
                        sr1.setStroke(Color.web("#a4c6b8"));
                        sr2.setStroke(Color.web("#a4c6b8"));
                        sr3.setStroke(Color.web("#a4c6b8"));
                        try {
                            g[3].add(paction, 0, 7);
                            g[3].add(sr1, 0, 8);
                            g[3].add(sr2, 0, 9);
                            g[3].add(sr3, 0, 10);
                            g[3].add(vBoxs, 0, 8);
                            g[3].add(vBoxs1, 0, 9);
                            g[3].add(vBoxs2, 0, 10);
                        }catch (Exception exception){

                        }

                        follow.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                try {

                                    current_user[0].addfollowing(temp.handle);
                                    temp.addfollower(current_user[0].handle);
                                } catch (IOException e) {

                                }
                                l[33].setText("   Followers: "+temp.follower_count);
                                l[11].setText(String.valueOf(current_user[0].follwing_count));
                            }
                        });
                        remove_follow.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                try {
                                    current_user[0].remove_following(temp.handle,users);
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    temp.remove_follower(current_user[0].handle,users);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                l[33].setText("   Followers: "+temp.follower_count);
                                l[11].setText(String.valueOf(current_user[0].follwing_count));
                            }
                        });
                    }
                }else l[30].setText("Enter Valid Data");
            }
        });
        for(int i=0;i< like.length;i++){
            int finalI = i;
            like[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                  like[finalI].setStyle("-fx-text-fill: red;");
                }
            });
        }
        next.setOnAction(new EventHandler<ActionEvent>() { //LOAD NEXT 5 TWEETS FROM QUEUE
            @Override
            public void handle(ActionEvent actionEvent) {
                for(int i=0;i< like.length;i++) like[i].setStyle("-fx-font-weight: bold;-fx-background-color: black; -fx-border-color: aqua");
               try {
                   vBox1.getChildren().addAll(l[14],l[15],l[16],like[0]);
                   vBox2.getChildren().addAll(l[17],l[18],l[19],like[1]);
                   vBox3.getChildren().addAll(l[20],l[21],l[22],like[2]);
                   vBox4.getChildren().addAll(l[23],l[24],l[25],like[3]);
                   vBox5.getChildren().addAll(l[26],l[27],l[28],like[4]);
                }catch (Exception e){

               }
                try {
                    boolean temp;
                    temp= current_user[0].read_tweets_for_user.display_tweets(l[14],l[15],l[16]);
                    if(temp) vBox1.getChildren().removeAll(l[14],l[15],l[16],like[0]);
                    temp= current_user[0].read_tweets_for_user.display_tweets(l[17],l[18],l[19]);
                    if(temp) vBox2.getChildren().removeAll(l[17],l[18],l[19],like[1]);
                    temp= current_user[0].read_tweets_for_user.display_tweets(l[20],l[21],l[22]);
                    if(temp) vBox3.getChildren().removeAll(l[20],l[21],l[22],like[2]);
                    temp= current_user[0].read_tweets_for_user.display_tweets(l[23],l[24],l[25]);
                    if(temp) vBox4.getChildren().removeAll(l[23],l[24],l[25],like[3]);
                    temp= current_user[0].read_tweets_for_user.display_tweets(l[26],l[27],l[28]);
                    if(temp)  vBox5.getChildren().removeAll(l[26],l[27],l[28],like[4]);
                }catch (Exception e){

                }
            }
        });
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(s[1]);
                name.clear();
                password.clear();
                l[8].setText(" ");
            }
        });
        for(int i=0;i< like.length;i++) like[i].setStyle("-fx-font-weight: bold;-fx-background-color: white; -fx-border-color: #08203e");


        stage.show();
    }

//HELPER FUNCTIONS
   public boolean verify_username(String username, Users users){
        if(users.user!=null){
            NodeUser ptr=users.user;
            while (ptr!=null){
                if(username.equals(ptr.handle)){
                    return false;
                }
                ptr=ptr.nextuser;
            }
        }
        return true;
   }
   public NodeUser verify_user(String username, String pass, Users users){
       if(users.user!=null){
           NodeUser ptr=users.user;
           while (ptr!=null){
               if(username.equals(ptr.handle) && pass.equals(ptr.password)){

                   return ptr;
               }
               ptr=ptr.nextuser;
           }
       }
       return null;
    }
    private String handleKeyTyped(KeyEvent event) {
        String typedChar = event.getCharacter();
        return typedChar;
    }

    private boolean handleKeyPressed(KeyEvent event) {
        return !event.getCode().equals(javafx.scene.input.KeyCode.BACK_SPACE);
    }

    public static void main(String[] args) {
        launch();
    }
}