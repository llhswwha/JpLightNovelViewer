package oneway.NovelViewer.Interface;

import java.util.LinkedList;

import oneway.NovelViewer.IndexTree.DivideWord;

/**
 * Created by Administrator on 2014/7/20.
 */
public interface IWordBase {
    String[] getTypes();

    IWordCollection get(int id);
    IWordCollection get(String type);

    boolean saveWords();
    LinkedList<DivideWord> getDivideWords(String sentence);

    int getCount();
}
