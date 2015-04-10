package oneway.NovelViewer.BaseClasses;

import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.WordInfos.WordHeadManager;

public class BaseWordInfo implements IWordInfo {
    protected String wordName;
    protected String[] wordNames;
    protected String wordType;
    protected BaseWordInfo(){

    }
    protected BaseWordInfo(String name)
    {
        wordName=name;
        //wordNames=name.split(" ");
    }


    protected BaseWordInfo(String name,String type)
    {
        this(name);
        wordType=type;
    }


    @Override
    public String getText(){
        return null;
    }

    @Override
    public String getType() {
        return wordType;
    }

    @Override
    public String getName() {
        return wordName;
    }

    public String[] getNames()
    {
        if(wordNames==null)
        {
            if(wordName.equals("です"))
            {
                wordNames = new String[]{"です","ではありません","ではありませんでした","ではあった","だ","ではない","だった","ではなかった","でしょう","である"};
            }
            wordNames = wordName.split(" ");
        }
        return wordNames;

        /*String[] names=wordName.split(" ");
        if(names.length>1)
        {
            String name2=names[1];
            name2="";
        }*/
    }


    public String GetTail() {
        return wordName.substring(wordName.length()-1);
    }

    public String GetKey() {
        //String[] parts=wordName.split(" ");
        String[] parts=getNames();
        return parts[parts.length-1];
    }

    @Override
    public String getHead(int length) {
        return wordName.substring(0, length);
    }

    @Override
    public boolean startsWith(String c) {
        String[] names=getNames();
        for(String name : names)
        {
            if(name.startsWith(c))
            {
                return true;
            }
        }
        return false;
    }
}
