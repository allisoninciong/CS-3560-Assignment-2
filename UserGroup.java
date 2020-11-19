package v4;

import java.util.ArrayList;
import java.util.List;

public class UserGroup extends SysEntry             // composite pattern (composite)
{
    // declare variables
    private String ID;

    // composite pattern
    private List<SysEntry> userGroup;

    public UserGroup(String ID)
    {
        // initialize variables
        ID = "";
        userGroup = new ArrayList<SysEntry>();

        setID(ID);
    }

    public void add(SysEntry sysEntry)
    {
        userGroup.add(sysEntry);
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getID()
    {
        return ID;
    }

    // visitor pattern
    @Override
    public String accept(VisitorInterface visitor)
    {
        return "";
    }
}
