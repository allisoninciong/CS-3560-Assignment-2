package v4;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class UserView
{
    // declare variables
    private ControlPanel controlPanel;
    private String userViewID, userID, tweet;

    // declare widgets
    private TextField userIDTextField, tweetMessageTextField;
    private Button followUserButton, postTweetButton;
    private List<String> currentlyFollowingList, newsFeedList;
    private ListView<String> currentlyFollowingListView, newsFeedListView;
    private Label creationTimeLabel, lastUpdateTimeLabel;

    public UserView(String ID)
    {
        // initialize variables
        controlPanel = ControlPanel.getInstance();
        userViewID = "";
        userID = "";
        tweet = "";
        creationTimeLabel = new Label("");
        lastUpdateTimeLabel = new Label("");

        // set ID and add user view
        this.userViewID = ID;
        controlPanel.addUserView(this);

        // initialize text areas
        userIDTextField = new TextField("User ID");
        tweetMessageTextField = new TextField("Tweet Message");

        // initialize buttons
        followUserButton = new Button("Follow User");
        postTweetButton = new Button("Post Tweet");

        // initialize listviews
        currentlyFollowingListView = new ListView<String>();
        currentlyFollowingListView.getItems().add("List View (Currently Following)");
        newsFeedListView = new ListView<String>();
        newsFeedListView.getItems().add("List View (News Feed)");
    }

    public String getUserViewID()
    {
        return userViewID;
    }

    public ListView<String> getNewsFeedListView()
    {
        return newsFeedListView;
    }

    public void displayFollowers(User user)
    {
        // get ID of user2
        userID = userIDTextField.getText();

        // check if user exists
        if(controlPanel.getUserList().contains(controlPanel.getUser(userID)))
        {
            // follow user2
            User user2 = controlPanel.getUser(userID);
            user.follow(user2);

            // add user to following list
            currentlyFollowingListView.getItems().add(userID);
        }
    }

    public void displayNewsFeed(User user)
    {
        // get tweet
        tweet = tweetMessageTextField.getText();
        tweet = user.getID() + ": " + tweet;
        user.setTweet(tweet);
        controlPanel.addTweet(tweet);

        // add tweet to the newsfeed of the current user
        newsFeedListView.getItems().add(1, tweet);

        // add tweet to newsfeed of followers
        for(String follower : user.getFollowersIDList())
        {
            controlPanel.getUserView(follower).newsFeedListView.getItems().add(1, tweet);
            controlPanel.getUserView(follower).lastUpdateTimeLabel.setText("Last Update Time: "
                    + System.currentTimeMillis() + " ms");
        }
    }

    public void start(User user)
    {
        // initialize lists
        currentlyFollowingList = user.getFollowingIDList();
        newsFeedList = user.getNewsFeed();

        // show previous following
        for(String following : currentlyFollowingList)
        {
            currentlyFollowingListView.getItems().add(following);
        }

        // event listener for clicking add group view button
        followUserButton.setOnAction(event ->
        {
            displayFollowers(user);
        });

        // show previous newsfeed tweets
        newsFeedList = user.getNewsFeed();
        Collections.reverse(newsFeedList);
        for(String tweet : newsFeedList)
        {
            this.getNewsFeedListView().getItems().add(tweet);
        }

        // event listener for clicking add group view button
        postTweetButton.setOnAction(event ->
        {
            displayNewsFeed(user);

            // displays last update time for current user
            lastUpdateTimeLabel.setText("Last Update Time: " + user.getLastUpdateTime() + " ms");
            user.setLastUpdateTime(System.currentTimeMillis());
        });

        // set creation time and last update time labels
        creationTimeLabel.setText("Creation Time: " + user.getCreationTime() + " ms");
        lastUpdateTimeLabel.setText("Last Update Time: " + user.getLastUpdateTime() + " ms");

        // format layout
        HBox hbox1 = new HBox(10, userIDTextField, followUserButton);
        HBox hbox2 = new HBox(10, tweetMessageTextField, postTweetButton);
        VBox vbox = new VBox(creationTimeLabel, lastUpdateTimeLabel);
        VBox vbox2 = new VBox(20, hbox1, vbox, currentlyFollowingListView, hbox2, newsFeedListView);

        // create stage and scene
        Stage secondaryStage = new Stage();
        Scene scene = new Scene(vbox2, 300, 500);
        secondaryStage.setScene(scene);
        secondaryStage.setTitle(user.getID() + "'s Profile");
        secondaryStage.show();
    }
}
