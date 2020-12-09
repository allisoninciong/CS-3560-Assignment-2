package v4;

import java.util.ArrayList;
import java.util.Collections;

public class Visitor implements VisitorInterface        // visitor pattern
{
    // declare variables
    private ControlPanel controlPanel;
    private String userList, groupList, positiveMessageList;
    private String userTotal, groupTotal, messageTotal, positiveMessagePercentage;
    private String idValidation;
    private String lastUpdatedUserID;
    private String lastUpdatedUserString;
    private int positiveMessages;
    private int max;
    private double percentage;
    private String[] users, groups;
    private ArrayList<Long> listOfLastUpdatedTimes;
    private User lastUpdatedUser;

    public Visitor()
    {
        userList = "";
        userTotal = "";
        groupList = "";
        groupTotal = "";
        messageTotal = "";
        positiveMessagePercentage = "";
        positiveMessageList = "";
        idValidation = "";
        lastUpdatedUserID = "";
        lastUpdatedUserString = "";
        positiveMessages = 0;
        percentage = 0;
        max = 0;
        listOfLastUpdatedTimes = new ArrayList<>();
    }

    public String loadUsersIntoList()
    {
        // reinitialize userList
        userList = "";

        // add each user to a list
        for(User user : controlPanel.getUserList())
        {
            userList += "\n" + user.getID();
        }

        return userList;
    }

    public String loadGroupsIntoList()
    {
        // reinitialize groupList
        groupList = "";

        // add each user group to a list
        for(UserGroup userGroup : controlPanel.getUserGroupList())
        {
            groupList += "\n" + userGroup.getID();
        }

        return groupList;
    }

    @Override
    public String visit(UserTotalVisitor userTotalVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        loadUsersIntoList();

        // string to display total users
        userTotal = "List of Users: " + userList;
        return userTotal;
    }

    @Override
    public String visit(GroupTotalVisitor groupTotalVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        loadGroupsIntoList();

        // string to display total user groups
        groupTotal = "List of User Groups: " + groupList;
        return groupTotal;
    }

    @Override
    public String visit(MessagesTotalVisitor messagesTotalVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        // string to display total messages
        messageTotal = "Message Total: " + controlPanel.getTweetList().size();
        return messageTotal;
    }

    @Override
    public String visit(PositivePercentageVisitor positivePercentageVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        // reinitialize positive messages count
        positiveMessages = 0;

        // count which tweets are positive (good, great, excellent)
        for(String tweet : controlPanel.getTweetList())
        {
            if(tweet.toLowerCase().contains("good") || tweet.toLowerCase().contains("great")
                    || tweet.toLowerCase().contains("excellent"))
            {
                positiveMessageList += "\n" + tweet;
                positiveMessages++;
            }
        }

        // calculate percentage
        percentage = (float) positiveMessages / controlPanel.getTweetList().size() * 100;
        percentage = Math.round(percentage * 100.0) / 100.0;        // round to two decimal places


        // string to display positive message percentage
        positiveMessagePercentage = "Positive Percentage: " + percentage + "%";
        return positiveMessagePercentage;
    }

    @Override
    public String visit(IDValidationVisitor idValidationVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        // declare booleans
        boolean containsDuplicate = false;
        boolean containsSpace = false;

        // load users and groups into list
        loadUsersIntoList();
        loadGroupsIntoList();

        // put list elements into an array
        users = userList.split("\\r?\\n");
        groups = groupList.split("\\r?\\n");

        // go through user list
        for(int i = 0; i < users.length; i++)
        {
            // check for space
            if(users[i].contains(" "))
            {
                containsSpace = true;
            }

            // check for duplicates
            for(int j = i + 1; j < users.length; j++)
            {
                if(users[i].equals(users[j]))
                {
                    containsDuplicate = true;
                }
            }
        }

        // go through group list
        for(int i = 0; i < groups.length; i++)
        {
            // check for space
            if(groups[i].contains(" "))
            {
                containsSpace = true;
            }

            // check for duplicates
            for(int j = i + 1; j < groups.length; j++)
            {
                if(groups[i].equals(groups[j]))
                {
                    containsDuplicate = true;
                }
            }
        }

        // if there are duplicates or if username contains space
        if(containsDuplicate || containsSpace)
        {
            idValidation = "IDs are not valid";
        }

        // if there are no duplicates and no space characters
        else
        {
            idValidation = "All IDs are valid";
        }

        return idValidation;
    }

    @Override
    public String visit(LastUpdatedUserVisitor lastUpdatedUserVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        // add every user's last update time to array list
        for(User user : controlPanel.getUserList())
        {
            // add times into array list
            listOfLastUpdatedTimes.add(user.getLastUpdateTime());
        }

        // calculate who is the most recent user
        max = listOfLastUpdatedTimes.indexOf(Collections.max(listOfLastUpdatedTimes));
        lastUpdatedUser = controlPanel.getUserList().get(max);
        lastUpdatedUserID = lastUpdatedUser.getID();

        // string to display last updated user
        lastUpdatedUserString = "Last Updated User: " + lastUpdatedUserID;
        return lastUpdatedUserString;
    }
}
