package v4;

public class MessagesTotalVisitor implements Visitable             // visitor pattern
{
    @Override
    public String accept(VisitorInterface visitor)
    {
        return visitor.visit(this);
    }
}
