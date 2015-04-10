package oneway.NovelViewer.WordChange;

public class ADJ2 {
    static String[] tails=new String[]{"に","な","ではない","なら","ならば","だった","で","さ"};
    public static String[] GetTails(String word)
    {
        int length=tails.length;
        String[] names=new String[length];
        for(int i=0;i<length;i++)
        {
            names[i]=word+tails[i];
        }
        return names;
    }
    public static String[] GetTails()
    {
        return tails;
    }
}
