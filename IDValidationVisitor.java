package v4;

public class IDValidationVisitor implements Visitable             // visitor pattern
{
    @Override
    public String accept(VisitorInterface visitor)
    {
        return visitor.visit(this);
    }
}
