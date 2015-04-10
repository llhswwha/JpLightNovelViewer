package oneway.NovelViewer.Interface;

/**
 * Created by Administrator on 2014/7/20.
 */
public interface IWordInfo {
    String getText();
    String getType();
    String getName();
    String[] getNames();

    String GetTail();
    String GetKey();
    String getHead(int length);

    boolean startsWith(String c);
}
