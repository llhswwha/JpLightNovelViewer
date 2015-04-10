package oneway.NovelViewer.IndexTree;

import java.util.LinkedList;

/**
 * Created by Administrator on 2015/4/6.
 */
public class DivideResult extends LinkedList<DivideWord> {

    //不在词库中的单词
    public void add(String word)
    {
        DivideWord dw=new DivideWord(word);
        this.add(dw);
    }
}
