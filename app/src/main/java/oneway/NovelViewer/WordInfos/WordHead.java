package oneway.NovelViewer.WordInfos;

import java.util.LinkedList;

import oneway.NovelViewer.IndexTree.NameInfo;
import oneway.NovelViewer.Interface.IWordInfo;

/**
 * Created by Administrator on 2015/4/4.
 */
public class WordHead {
    //public LinkedList<IWordInfo> Items = new LinkedList<IWordInfo>();
    public String Text;
    //public LinkedList<String> WordNames = new LinkedList<String>();
    public LinkedList<NameInfo> Items = new LinkedList<NameInfo>();

    public WordHead(String t)
    {
        Text = t;
    }

    public void add(NameInfo word)
    {
        Items.add(word);
        //LinkedList<String> names = word.GetTextByHead(Text);
    }

    public String toString()
    {
        return Text;
    }

    public LinkedList<NameInfo> getWords()
    {
        return Items;
    }

    //public LinkedList<String> GetNames()
    //{
        //return WordNames;
    //}
}
