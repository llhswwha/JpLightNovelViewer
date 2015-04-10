package oneway.NovelViewer.WordBase;

import java.io.File;
import java.util.LinkedList;

import oneway.NovelViewer.BaseClasses.BaseWordInfo;
import oneway.NovelViewer.Data.FileNames;
import oneway.NovelViewer.Data.MyFileIO;
import oneway.NovelViewer.Interface.IWordInfo;

public class WordInfo extends BaseWordInfo {
    public String path;
    public WordInfo(String t,String n,String p)
    {
        super(t,n);
        path=p;
    }
    public WordInfo(String n)
    {
        super(n);
    }

    public WordInfo(File fi)
    {
        String name = fi.getName();
        int id = name.indexOf(".");
        wordName = name.substring(0,id);
        path = fi.getPath();
    }
    public WordInfo(String name, String wordType) {
        this.wordType=wordType;
        path=FileNames.dirPathWordBase+wordType+"//"+name+".txt";
        wordName=name;
        if(wordType=="三类动词"&&wordName.endsWith("する"))
        {
            wordName=wordName.replaceAll("する", "");
        }
    }
    public String getText() {
        String text=MyFileIO.ReadAllText(path);
        return text;
    }

    public String GetFirstChar()
    {
        Character c=wordName.charAt(0);
        return c.toString();
    }
    public String GetChar(int id3) {
        Character c=wordName.charAt(id3);
        return c.toString();
    }

    public int GetLength() {
        return wordName.length();
    }

    @Override
    public String toString() {
        return wordName;
    }
}
