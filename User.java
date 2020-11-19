package v4;

import java.util.ArrayList;
import java.util.List;

public class User extends Subject           // composite pattern (leaf)
                                            // observer pattern (concrete subject, concrete observer)
{
    // declare variables
    private String tweet;
    private String ID;
    private List<String> newsFeed;
    private List<String> followingIDList;
    private List<String> followersIDList;


    public User(String ID)
    {
        // initialize variables
        tweet = "";
        ID = "";
        newsFeed = new ArrayList<String>();
        followingIDList = new ArrayList<String>();
        followersIDList = new ArrayList<String>();

        setID(ID);
    }

    public List<String> getNewsFeed()
    {
        return newsFeed;
    }

    public void setTweet(String tweet)
    {
        this.tweet = tweet;
        notifyAllFollowers();
        newsFeed.add(tweet);
    }

    public String getTweet()
    {
        return tweet;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getID()
    {
        return ID;
    }

    public List<String> getFollowingIDList()
    {
        for(User user : following)
        {
            followingIDList.add(user.getID());
        }
        return followingIDList;
    }

    public List<String> getFollowersIDList()
    {
        for (User user : followers)
        {
            followersIDList.add(user.getID());
        }
        return followersIDList;
    }

    // visitor pattern
    @Override
    public String accept(VisitorInterface visitor)
    {
        return "";
    }
}