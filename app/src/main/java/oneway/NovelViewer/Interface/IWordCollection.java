package oneway.NovelViewer.Interface;

import java.util.LinkedList;

/**
 * Created by Administrator on 2014/7/20.
 */
public interface IWordCollection {
    String getWordType();
    void add(String wordName);
    void add(IWordInfo wordInfo);
    IWordInfo get(int id);
    LinkedList<String> getWordNames();
}
