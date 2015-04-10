package oneway.NovelViewer.BaseClasses;

import java.util.LinkedList;

import oneway.NovelViewer.IndexTree.DivideWord;
import oneway.NovelViewer.IndexTree.WITree;
import oneway.NovelViewer.Interface.*;
import oneway.NovelViewer.WordInfos.WordHeadManager;


public class BaseWordBase extends LinkedList<IWordCollection> implements IWordBase {
    protected LinkedList<IWordInfo> allWords = new LinkedList<IWordInfo>();
    protected String[] wordTypes;
    public String[] getTypes()//得到所有单词类型
    {
        return wordTypes;
    }

    public int getCount()
    {
        return allWords.size();
    }

    public IWordCollection get(String type) {
        /*int id=wordTypes.indexOf(type);
        if(id!=-1)
            return this.get(id);
        else
            return null;
            */
        for (int i=0;i<wordTypes.length;i++)
        {
            if(wordTypes[i].equals(type))
            {
                return this.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean saveWords() {
        return false;
    }

    //直接根据词名开始切词
    protected static WITree wiTree;
    public LinkedList<DivideWord> getDivideWords(String sentence)//对句子进行切词
    {
        if(wiTree==null)
            wiTree=new WITree(allWords);//根据LinkedList<WordInfo> allWords 建立词库索引树（切词树）
        return wiTree.divideSentence(sentence);
    }

    protected void addWord(IWordInfo word)
    {
        allWords.add(word);
        WordHeadManager.Add(word);
    }

    protected void addWords(LinkedList<IWordInfo> words)
    {
        allWords.addAll(words);
        for (IWordInfo word : words)
        {
            WordHeadManager.Add(word);
        }
    }
}
