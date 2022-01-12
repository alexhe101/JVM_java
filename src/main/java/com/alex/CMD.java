package com.alex;

import jdk.nashorn.internal.ir.Flags;
import org.apache.commons.cli.*;

import java.util.Arrays;

/**
 * @program: jvm
 * @description: cmd parser
 * @author: alex101
 * @create: 2022-01-12 16:21
 **/
public class CMD {
    private boolean helpFlag;
    private boolean versionFlag;
    private String cpOption;
    private String cls;//class
    private String XjreOption;
    private String []args;
    private  Options options = new Options();

    public CMD() {
        initCliArgs();
    }

    private void initCliArgs()
    {
        options.addOption("help",false,"print the help message");
        options.addOption("?",false,"print the help message");
        options.addOption("version",false,"print version and exit");
        options.addOption("classpath",true,"classpath");
        options.addOption("cp",true,"classpath");
        options.addOption("Xjre",true,"path to jre");
    }

    public void printUsage(String arg)
    {
        System.out.printf("Usage:%s [-option] class [args...]\n",arg);
    }
    public void processCMD(String []args)
    {
        this.args = args;
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options,args);
            if(cmd.hasOption("version")){
                versionFlag = true;
            }
            if(cmd.hasOption("help")||cmd.hasOption("?")){
                helpFlag=true;
            }
            cpOption = cmd.getOptionValue("cp");
            if(cpOption==null)
            {
                cpOption=cmd.getOptionValue("classpath");
            }
            XjreOption = cmd.getOptionValue("Xjre");
            if(args.length>0&&versionFlag==false&&helpFlag==false){
                cls = cmd.getArgs()[0];
                this.args = Arrays.copyOfRange(cmd.getArgs(),1,cmd.getArgs().length);
            }
        } catch (ParseException e) {
            printUsage(args[0]);
        }
    }

    public boolean isHelpFlag() {
        return helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public String getCpOption() {
        return cpOption;
    }

    public String getCls() {
        return cls;
    }

    public String[] getArgs() {
        return args;
    }

    public Options getOptions() {
        return options;
    }
}
