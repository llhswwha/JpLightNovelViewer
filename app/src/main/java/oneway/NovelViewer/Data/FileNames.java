package oneway.NovelViewer.Data;

import java.io.File;

import android.os.Environment;

public class FileNames {
    private static String path0="/[oneway]/MyProgram/NovelViewer/";
    private static String path1="JPNovels/";
    private static String path2="JPWords/";
    private static String path3="JPNewWords/";
    public static String rootPath;
    public static String dirPathNovelBase;
    public static String dirPathWordBase;
    public static String dirPathNewWords;
    //public static String  dirPathIndexTree;
    public static String filePathWordBaseInfo;
    public static String filePathWordTailInfo;
    public static String dirDb;
    static File SDPath;
    public FileNames()
    {
        if(rootPath!=null)return;
        SDPath = Environment.getExternalStorageDirectory();
        rootPath= SDPath.getPath()+path0;
        MyFileIO.CreateDirPath(rootPath);
        dirPathNovelBase = rootPath+path1;
        MyFileIO.CreateDirPath(dirPathNovelBase);
        dirPathWordBase = rootPath+path2;
        MyFileIO.CreateDirPath(dirPathWordBase);
        dirPathNewWords = rootPath+path3;
        MyFileIO.CreateDirPath(dirPathNewWords);
        filePathWordBaseInfo=dirPathWordBase+"词库信息.ini";
        filePathWordTailInfo=dirPathWordBase+"变形信息.ini";
        //dirPathIndexTree=rootPath+"索引树/";
        //MyFileIO.CreateDirPath(dirPathIndexTree);

        dirDb= rootPath+"DataBase/";
        MyFileIO.CreateDirPath(dirDb);
    }
    public static String GetFileName(String path,String exetension)
    {
        String fileName = String.format("%s%s%s", rootPath, path, exetension);
        return fileName;
    }
}
