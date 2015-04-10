package oneway.NovelViewer.WordChange;

public class Verb2 {
    static String[] tails2=new String[]{"り","","ない","ば","れば","られる","させる","て","た"};

    static String[] tails=new String[]{"","り","ます","ない","なく","ず","よう","ろ","ば","れば","られる","させる","て","た"
            ,"たり","らなく","らずに","らず","らなかった","らたい","らたく","らたかった","らたくない","らたくなかった"
            ,"られ","られて","られた","らせ","らせて","らせた","れて","れた","れない","れなく","れなかった","ました"
            ,"ません","たら","ている","るべき","てきた"};

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
