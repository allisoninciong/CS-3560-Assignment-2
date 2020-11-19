package v4;

public class PositivePercentageVisitor implements Visitable             // visitor pattern
{
    @Override
    public String accept(VisitorInterface visitor)
    {
        return visitor.visit(this);
    }
}
