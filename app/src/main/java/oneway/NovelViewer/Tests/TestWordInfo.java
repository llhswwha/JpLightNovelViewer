package oneway.NovelViewer.Tests;

import oneway.NovelViewer.BaseClasses.BaseWordInfo;

/**
 * Created by Administrator on 2015/4/9.
 */
public class TestWordInfo extends BaseWordInfo {
    private String text;
    public TestWordInfo(String name,String text)
    {
        super(name);
        this.text=text;
    }
    @Override
    public String getText() {
        return text;
    }
}
