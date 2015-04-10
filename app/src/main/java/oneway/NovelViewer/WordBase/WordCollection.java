package oneway.NovelViewer.WordBase;

import java.io.File;
import java.util.LinkedList;

import oneway.NovelViewer.BaseClasses.BaseWordCollection;
import oneway.NovelViewer.Data.MyFileIO;
import oneway.NovelViewer.Interface.IWordCollection;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.Word.Word;

public class WordCollection extends BaseWordCollection {
    public WordCollection(File subDir)
    {
        wordType = subDir.getName();

        LinkedList<File> fis = MyFileIO.GetSubFiles(subDir);
        for(File fi : fis)
        {
            WordInfo wi = new WordInfo(fi);
            this.add(wi);
        }
    }
    public WordCollection(String line) //输入的是<词性>
    {
        // TODO Auto-generated constructor stub
        wordType=line.substring(1,line.length()-1);
        if(wordType.contains("("))
            wordType=wordType.substring(1,wordType.length());
//    	wordType="";
//    	wordType=line.substring(1,line.length()-1);
    }
    public WordCollection() {
        // TODO Auto-generated constructor stub
    }
    /*public LinkedList<Word> GetAllWords()
    {
        LinkedList<Word> words = new LinkedList<Word>();
        for (IWordInfo wi : wis)
        {
            //Word word = Word.Create(wordType,wi.path);
            Word word = new Word(wordType,wi.path);
            words.add(word);
        }
        return words;
    }*/
    public void Add(File fi)
    {
        WordInfo wi = new WordInfo(fi);
        this.add(wi);
    }
    public void Add(String line)//输入的是词名
    {
        WordInfo wi = new WordInfo(line,wordType);
        this.add(wi);
    }
    @Override
    public String toString() {
        String text="("+wordType+")";
        for(IWordInfo wi : items)
        {
            text+="\n"+wi.getName();
        }
        return text;
    }

}
