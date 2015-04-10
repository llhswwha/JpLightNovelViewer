package oneway.NovelViewer.WordChange;

public class Verb3 {

    static String[] tails2=new String[]{"し","します","","して","した","する","される"};

    static String[] tails=new String[]{"し","します","","しない","しなかった","しよう","しろ","すれば","できる","される","させる","して","している","していた","した","する"};

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
