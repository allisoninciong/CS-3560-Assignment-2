package v4;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject extends SysEntry implements Observer      // observer pattern (subject)
{
    public List<User> following;
    public List<User> followers;

    public Subject()
    {
        // initialize lists
        following = new ArrayList<User>();
        followers = new ArrayList<User>();
    }

    public void follow(User user)
    {
        // follow user (add to following list)
        following.add(user);

        // add yourself to their followers
        user.followers.add((User)this);
    }

    public void notifyAllFollowers()
    {
        for (User user: followers)
        {
            update(user);
        }
    }

    public void update(User user)
    {
        user.getNewsFeed().add(user.getTweet());
    }
}
