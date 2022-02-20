import com.alex.rtda.Frame;
import com.alex.rtda.LocalVars;
import com.alex.rtda.OperandStack;

public class TestLocalVals04 {
    public static void main(String[] args) {
        Frame frame = new Frame(100,100);
        testLocalVars(frame.getLocalVars());
        testOperandStack(frame.getOperandStack());
    }

    private static void testOperandStack(OperandStack operandStack) {
        operandStack.pushInt(100);
        operandStack.pushInt(-100);
        operandStack.pushLong(2997924580l);
        operandStack.pushLong(-2997924580l);
        operandStack.pushFloat(3.1415926f);
        operandStack.pushDouble(2.71828182845);
        operandStack.pushRef(null);
        System.out.println(operandStack.popRef());
        System.out.println(operandStack.popDouble());
        System.out.println(operandStack.popFloat());
        System.out.println(operandStack.popLong());
        System.out.println(operandStack.popLong());
        System.out.println(operandStack.popInt());
        System.out.println(operandStack.popInt());
    }

    public static void testLocalVars(LocalVars localVars)
    {
        localVars.setInt(0,100);
        localVars.setInt(1,-100);
        localVars.setLong(2,2997924580l);
        localVars.setLong(4,-2997924580l);
        localVars.setFloat(6,3.1415926f);
        localVars.setDouble(7,2.71828182845);
        localVars.setRef(9,null);
        System.out.println(localVars.getInt(0));
        System.out.println(localVars.getInt(1));
        System.out.println(localVars.getLong(2));
        System.out.println(localVars.getLong(4));
        System.out.println(localVars.getFloat(6));

        System.out.println(localVars.getDouble(7));

        System.out.println(localVars.getRef(9));
    }
}
