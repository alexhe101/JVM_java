import com.alex.CMD;
import com.alex.Interpreter;
import com.alex.JVM;
import com.alex.classfile.ClassFile;
import com.alex.classfile.MemberInfo;
import com.alex.classpath.ClassPath;

public class TestInterpreter05 {
    public static void main(String[] args) {
        CMD cmd = new CMD();
        cmd.processCMD(args);
        ClassPath classPath = new ClassPath(cmd.getXjreOption(),cmd.getCpOption());
        String className = cmd.getCls().replaceAll("\\.","/");
        ClassFile classFile = loadClass(className,classPath);
        MemberInfo mainMethod = getMainMethod(classFile);
        if(mainMethod!=null)
        {
            Interpreter.interpret(mainMethod);
        }else {
            System.out.println("not found method");
        }
    }

    private static MemberInfo getMainMethod(ClassFile classFile) {
        MemberInfo[] memberInfo = classFile.getMethods();
        for (int i = 0; i < memberInfo.length; i++) {
            if(memberInfo[i].getName().equals("main")&&memberInfo[i].getDescriptor().equals("([Ljava/lang/String;)V"))
            {
                return memberInfo[i];
            }
        }
        return null;
    }

    private static ClassFile loadClass(String className, ClassPath classPath) {
        byte[] classData = classPath.readClass(className);
        return new ClassFile(classData);
    }
}
