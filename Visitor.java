package v4;

public class Visitor implements VisitorInterface        // visitor pattern
{
    // declare variables
    private ControlPanel controlPanel;
    private String userList, groupList, positiveMessageList;
    private String userTotal, groupTotal, messageTotal, positiveMessagePercentage;
    private int positiveMessages;
    private double percentage;

    public Visitor()
    {
        userList = "";
        userTotal = "";
        groupList = "";
        groupTotal = "";
        messageTotal = "";
        positiveMessagePercentage = "";
        positiveMessageList = "";
        positiveMessages = 0;
        percentage = 0;
    }

    @Override
    public String visit(UserTotalVisitor userTotalVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        // reinitialize userList
        userList = "";

        // add each user to a list
        for(User user : controlPanel.getUserList())
        {
            userList += "\n" + user.getID();
        }

        // string to display total users
        userTotal = "List of Users: " + userList;
        return userTotal;
    }

    @Override
    public String visit(GroupTotalVisitor groupTotalVisitor)
    {
        controlPanel = ControlPanel.getInstance();

        // reinitialize groupList
        groupList = "";

        // add each user group to a list
        for(UserGroup userGroup : controlPanel.getUserGroupList())
        {
            groupList += "\n" + userGroup.getID();
        }

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
}
