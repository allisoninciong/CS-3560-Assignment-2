package v4;

public class GroupTotalVisitor implements Visitable             // visitor pattern
{
    @Override
    public String accept(VisitorInterface visitor)
    {
        return visitor.visit(this);
    }
}
