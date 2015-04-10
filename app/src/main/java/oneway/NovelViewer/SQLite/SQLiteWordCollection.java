package oneway.NovelViewer.SQLite;

import oneway.NovelViewer.BaseClasses.BaseWordCollection;
import oneway.NovelViewer.Interface.IWordCollection;
import oneway.NovelViewer.WordBase.WordInfo;

/**
 * Created by Administrator on 2014/7/20.
 */
public class SQLiteWordCollection extends BaseWordCollection {

    public SQLiteWordCollection(String type)
    {
        wordType=type;
    }

    @Override
    public void add(String wordName) {
        SQLiteWordInfo wordInfo=new SQLiteWordInfo(wordName,wordType);
        this.add(wordInfo);
    }
}
