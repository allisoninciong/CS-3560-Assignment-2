package v4;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class ControlPanel
{
    // singleton pattern (static instance)
    private static ControlPanel instance = null;

    // visitor pattern
    private Visitor visitor = new Visitor();

    // declare widgets
    private List<UserView> userViewList;
    private List<User> userList;
    private List<UserGroup> userGroupList;
    private List<String> tweetList;
    private TreeItem<String> childNode;

    // singleton pattern (private constructor)
    private ControlPanel()
    {
        // initialize array lists
        userViewList = new ArrayList<UserView>();
        userList = new ArrayList<User>();
        userGroupList = new ArrayList<UserGroup>();
        tweetList = new ArrayList<>();
    }

    // singleton pattern (static getter)
    public static ControlPanel getInstance()
    {
        if(instance == null)
        {
            instance = new ControlPanel();
        }
        return instance;
    }

    public TreeItem<String> addUser(String ID, TreeItem<String> parentNode)
    {
        // create new user
        User user = new User(ID);
        user.setID(ID);
        userList.add(user);

        // create new node on tree
        childNode = new TreeItem<String>(user.getID());
        childNode.setExpanded(true);
        parentNode.getChildren().add(childNode);

        return childNode;
    }

    public TreeItem<String> addGroup(String ID, TreeItem<String> parentNode)
    {
        // create new user group
        UserGroup userGroup = new UserGroup(ID);
        userGroup.setID(ID);
        userGroupList.add(userGroup);

        // create new node on tree
        childNode = new TreeItem<String>(userGroup.getID()); // use ID?
        childNode.setExpanded(true);
        parentNode.getChildren().add(childNode);

        return childNode;
    }

    public void addTweet(String tweet)
    {
        tweetList.add(tweet);
    }

    public void addUserView(UserView userView)
    {
        userViewList.add(userView);
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public List<UserGroup> getUserGroupList()
    {
        return userGroupList;
    }

    public List<String> getTweetList()
    {
        return tweetList;
    }

    public User getUser(String ID)
    {
        for(User user : userList)
        {
            if(user.getID().equals(ID))
            {
                return user;
            }
        }
        return null;
    }

    public UserView getUserView(String userViewID)
    {
        for(UserView userView : userViewList)
        {
            if(userView.getUserViewID().equals(userViewID))
            {
                return userView;
            }
        }
        return null;
    }

    // visitor pattern (user total)
    public String getUserTotal()
    {
        UserTotalVisitor userTotalVisitor = new UserTotalVisitor();
        return userTotalVisitor.accept(visitor);
    }

    // visitor pattern (group total)
    public String getGroupTotal()
    {
        GroupTotalVisitor groupTotalVisitor = new GroupTotalVisitor();
        return groupTotalVisitor.accept(visitor);
    }

    // visitor pattern (message total)
    public String getMessageTotal()
    {
        MessagesTotalVisitor messagesTotalVisitor = new MessagesTotalVisitor();
        return messagesTotalVisitor.accept(visitor);
    }

    // visitor pattern (positive percentage)
    public String getPositiveMessagePercentage()
    {
        PositivePercentageVisitor positivePercentageVisitor = new PositivePercentageVisitor();
        return positivePercentageVisitor.accept(visitor);
    }

    // visitor pattern (ID validation)
    public String getIDValidation()
    {
        IDValidationVisitor idValidationVisitor = new IDValidationVisitor();
        return idValidationVisitor.accept(visitor);
    }

    // visitor pattern (ID validation)
    public String getLastUpdatedUser()
    {
        LastUpdatedUserVisitor lastUpdatedUserVisitor = new LastUpdatedUserVisitor();
        return lastUpdatedUserVisitor.accept(visitor);
    }
}