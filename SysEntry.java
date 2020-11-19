package v4;

public abstract class SysEntry implements Visitable         // composite pattern (component)
{
    // declare variable
    private String ID;

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getID()
    {
        return ID;
    }
}
