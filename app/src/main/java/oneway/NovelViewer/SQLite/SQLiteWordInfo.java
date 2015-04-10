package oneway.NovelViewer.SQLite;

import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

import oneway.NovelViewer.BaseClasses.BaseWordInfo;
import oneway.NovelViewer.Helpers.SQLiteHelper;

/**
 * Created by Administrator on 2014/7/20.
 */
public class SQLiteWordInfo extends BaseWordInfo {
    public SQLiteWordInfo(String name, String type) {
        super(name,type);
    }
    private SQLiteDatabase db;
    public SQLiteWordInfo(String name,String type,SQLiteDatabase db)
    {
        this(name,type);
        this.db=db;
    }

    @Override
    public String getText() {
        String sql="Select Content from "+wordType+" where Name='"+wordName+"'";
        String[] items= SQLiteHelper.getItems(db, sql);
        if(items.length>0)
        {
            return items[0];
        }
        return null;
    }
}
