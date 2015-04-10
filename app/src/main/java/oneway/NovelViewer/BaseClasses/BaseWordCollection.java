package oneway.NovelViewer.BaseClasses;

import java.util.LinkedList;
import oneway.NovelViewer.Interface.*;

public class BaseWordCollection  implements IWordCollection {
    protected LinkedList<IWordInfo> items;
    protected String wordType;

    protected BaseWordCollection()
    {
        items =new LinkedList<IWordInfo>();
    }

    @Override
    public String getWordType() {
        return wordType;
    }

    @Override
    public void add(String wordName) {
        //todo:
    }

    @Override
    public void add(IWordInfo wordInfo) {
        items.add(wordInfo);
    }

    @Override
    public IWordInfo get(int id) {
        return items.get(id);
    }

    @Override
    public LinkedList<String> getWordNames() {
        LinkedList<String> words = new LinkedList<String>();
        for (IWordInfo wi : items)
        {
            String word = wi.getName();
            words.add(word);
        }
        return words;
    }

    public LinkedList<IWordInfo> getWords() {
        return items;
    }

}
