package oneway.NovelViewer.Helpers;

import oneway.NovelViewer.IndexTree.WIBase;
import oneway.NovelViewer.WordBase.WordBaseHelper;

/**
 * Created by Administrator on 2015/4/4.
 */
public class AppContext {
    //public static int nodeCount;
    //public static int wordCount;

    public static float getWordBaseTime;

    public static float allDivideTime;
    public static float dynamicLoadWordsTime;
    public static float divideSentenceTime;
    public static String getWordBaseDetails;
    //public static float loadNovelsTime;
    public static String getInfo()
    {
        String text="";
        //text+=String.format("加载小说:%f\n", loadNovelsTime);
        text+=String.format("导入词库时间:%.2f\n", getWordBaseTime);
        text+="词库数量:"+WordBaseHelper.getInstance().getCount()+"\n";
        text+=String.format("节点数量:%d\n", WIBase.nodeCount);
        text+=String.format("本次总用时:%.2f\n", allDivideTime);

        text+=String.format("    导入词汇时间:%.2f\n", dynamicLoadWordsTime);
        text+=String.format("    切词时间:%.2f\n", divideSentenceTime);
        return text;
    }
}
