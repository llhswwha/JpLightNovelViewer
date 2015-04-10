package oneway.NovelViewer.Helpers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.util.Log;

import java.util.LinkedList;

import oneway.NovelViewer.Data.FileNames;

/**
 * Created by Administrator on 2014/7/19.
 */
public class SQLiteHelper {
    private static String TAG="SQLiteHelper";
    public static void Create()
    {
        FileNames fileNames=new FileNames();
        String path=FileNames.dirDb+"Jp.db3";
        Log.e(TAG, path);
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(path,null);
        String sql="create table [一类动词]("
                +" [Name] varchar(255) primary key,"
                +" [Content] varchar(255))";
        db.execSQL(sql);

        sql="insert into [一类动词]([Name],[Content]) values ('1','2')";
        db.execSQL(sql);
    }

    public static  String[] getTableNames(SQLiteDatabase db)
    {
        String sql="SELECT name FROM sqlite_master WHERE type='table'";
        return getItems(db,sql);
    }

    public static  String[] getItems(SQLiteDatabase db,String table,String column,String where)
    {
        String sql="SELECT "+column+" FROM "+table+where;
        return getItems(db,sql);
    }

    public static  String[] getItems(SQLiteDatabase db,String sql)
    {
        Cursor cursor=db.rawQuery(sql,null);
        return getItems(cursor);
    }

    public static  String[] getItems(Cursor cursor)
    {
        int count=cursor.getCount();
        String[] items=new String[cursor.getCount()];

        for(int i=0;i<count;i++)
        {
            cursor.moveToNext();
            String item=cursor.getString(0);
            items[i]=item;
        }

        /*LinkedList<String> items=new LinkedList<String>();
        while(cursor.moveToNext())
        {
            for(int i=0;i<cursor.getColumnCount();i++)
            {
                String item=cursor.getString(i);
                items.add(item);
            }
        }*/

        return items;
    }
}
