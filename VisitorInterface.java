package v4;

public interface VisitorInterface           // visitor pattern
{
    String visit(UserTotalVisitor userTotalVisitor);
    String visit(GroupTotalVisitor groupTotalVisitor);
    String visit(MessagesTotalVisitor messagesTotalVisitor);
    String visit(PositivePercentageVisitor positivePercentageVisitor);
}
