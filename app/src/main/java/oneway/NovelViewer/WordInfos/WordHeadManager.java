package oneway.NovelViewer.WordInfos;

import java.util.Hashtable;
import java.util.LinkedList;

import oneway.NovelViewer.IndexTree.NameInfo;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.Word.WuShiYin;

/**
 * Created by Administrator on 2015/4/4.
 */
public class WordHeadManager {
    public static int count=0;
    public static Hashtable Items = new Hashtable();
    public static Hashtable Items2 = new Hashtable();
    //public static TimeSpan Time = new TimeSpan(0);

    /// <summary>
    /// 根据单词的开头添加信息
    /// </summary>
    /// <param name="word"></param>
    public static void Add(IWordInfo word)
    {
        if (word == null)
        {
            return;
        }
        count++;
        String[] names=word.getNames();
        AddHeads(word,names, 1, Items);
        //AddHeads(word,names, 2, Items2);
    }

    private static void AddHeads(IWordInfo word,String[] names,int length,Hashtable hashtable)
    {
        /*
        LinkedList<String> heads = GetHeads(names, length);
        for (String head : heads)
        {
            WordHead item = (WordHead)hashtable.get(head);
            if (item == null)
            {
                item = new WordHead(head);
                hashtable.put(head, item);
            }
        }*/
        for (String name : names)
        {
            if(name.trim().length()==0)continue;
            String head = name.substring(0, length);
            WordHead item = (WordHead)hashtable.get(head);
            if (item == null)
            {
                item = new WordHead(head);
                hashtable.put(head, item);
            }
            NameInfo nameInfo=new NameInfo(name,word);
            item.add(nameInfo);
        }
    }

    public static WordHead Get(String head)
    {
        WordHead item = null;
        if (head.length() == 2)
        {
            item = (WordHead)Items2.get(head);
        }
        else
        {
            item = (WordHead)Items.get(head);
        }
        return item;
    }

    public static LinkedList<String> GetHeads(String[] names, int length)
    {
        LinkedList<String> heads = new LinkedList<String>();
        for (String name : names)
        {
            if (name == "") continue;

            if(name.length()<length)continue;

            String h = name.substring(0, length);
            if (WuShiYin.IsAllPing(h))
            {
                String h2 = WuShiYin.ConvertToPian(h);
                if (!heads.contains(h2))
                    heads.add(h2);
            }
            if (!heads.contains(h))
                heads.add(h);
        }
        return heads;
    }

    /// <summary>
    /// 获取以该字符开头的所有单词
    /// </summary>
    /// <param name="head"></param>
    /// <returns></returns>
    public static LinkedList<NameInfo> GetWords(String head)
    {
        LinkedList<NameInfo> words=new LinkedList<NameInfo>();
        WordHead item1 = Get(head);
        if (item1 != null)
        {
            words.addAll(item1.getWords());
        }

        String lower = head.toLowerCase();
        if (lower != head)
        {
            WordHead item2 = Get(lower);
            if (item2 != null)
            {
                words.addAll(item2.getWords());
            }
        }
        return words;
    }

    public static void Clear()
    {
        Items = new Hashtable();
        Items2 = new Hashtable();
    }
}
