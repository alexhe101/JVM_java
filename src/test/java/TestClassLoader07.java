import com.alex.CMD;
import com.alex.Interpreter;
import com.alex.classpath.ClassPath;
import com.alex.rtda.heap.ClassLoader;
import com.alex.rtda.heap.Clazz;
import com.alex.rtda.heap.Method;

public class TestClassLoader07 {
    public static void main(String[] args) {
        CMD cmd = new CMD();
        cmd.processCMD(args);
        ClassPath classPath = new ClassPath(cmd.getXjreOption(),cmd.getCpOption());
        String className = cmd.getCls().replaceAll("\\.","/");
        ClassLoader classLoader = new ClassLoader(classPath,cmd.isVerboseClassFlag());
        Clazz mainClass = classLoader.loadClass(className);
        Method mainMethod = mainClass.getMainMethod();
        if(mainMethod!=null)
        {
            Interpreter.interpret(mainMethod, cmd.isVerboseInstFlag());
        }else {
            System.out.println("Main method not found "+cmd.getCls());
        }
    }
}
