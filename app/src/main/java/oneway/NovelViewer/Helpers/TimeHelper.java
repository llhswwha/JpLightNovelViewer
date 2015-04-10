package oneway.NovelViewer.Helpers;

/**
 * Created by Administrator on 2015/4/4.
 */
public class TimeHelper {
    public static float getTime(long beforeTime)
    {
        long afterTime=System.currentTimeMillis();
        float timeDistance=afterTime-beforeTime;
        float seconds=timeDistance/1000f;
        //long seconds=timeDistance/1000;
        return seconds;
    }
}
