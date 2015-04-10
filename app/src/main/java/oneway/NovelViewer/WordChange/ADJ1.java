package oneway.NovelViewer.WordChange;

public class ADJ1 {
    static String[] tails=new String[]{"","く","くない","ければ","かった","くて","かったり","かったら","かろ","すぎ","さ","げ","な","に"};

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
