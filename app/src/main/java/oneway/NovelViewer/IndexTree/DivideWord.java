package oneway.NovelViewer.IndexTree;

import java.util.LinkedList;

import oneway.NovelViewer.Interface.IWordInfo;

public class DivideWord {

    public String realWord;
    public LinkedList<IWordInfo> originalWord;
    public DivideWord(String w)
    {
        realWord=w;
    }
    public DivideWord(String w, LinkedList<IWordInfo> words) {
        realWord=w;
        originalWord=words;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return realWord;
    }
    public int GetWordCount()
    {
        if(originalWord==null)
            return 0;
        return originalWord.size();
    }
    public String GetWordText(int id)
    {
        IWordInfo wi=originalWord.get(id);
        /*String fileName=wi.path;
        String fileContent=MyFileIO.ReadAllText(fileName);
        return fileContent;*/
        return wi.getText();
    }
    public String GetAllWords()
    {
        String allWords="";
        for(IWordInfo wi : originalWord)
        {
            allWords+=wi.getName()+"\n";
        }
        return allWords;
    }

    public String[] getNames()
    {
        String[] names=new String[originalWord.size()];
        for (int i=0;i<names.length;i++)
        {
            names[i]=originalWord.get(i).getName();
        }
        return names;
    }

    public String GetMostPossibleWordText()
    {
        if(mostPosibleWord!=null)
        {
            IWordInfo wi=mostPosibleWord;
            return wi.getText();
        }
        else
            return null;
    }

    IWordInfo mostPosibleWord;
    //能否从多个候选词中找出最可能的词
    public boolean CanBeOnly()
    {
        LinkedList<String> posibleWords=new LinkedList<String>();
        for(IWordInfo wi : originalWord)
        {
            String[] names=wi.getNames();
            if(names.length==1)
            {
                mostPosibleWord=wi;
                return true;
            }
            if(realWord.equals(names[0]))//再次缩小范围
            {
                posibleWords.add(names[0]);
                mostPosibleWord=wi;
            }
            mostPosibleWord=wi;
        }
        return posibleWords.size()==1;
    }
    public int GetLength()
    {
        return realWord.length();
    }

}
