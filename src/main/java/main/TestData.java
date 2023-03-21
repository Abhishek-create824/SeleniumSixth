package main;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name="inputted")
    public Object[][] getObject()
    {
        Object[][] obj=new Object[2][2];
        {
            obj[0][0]="gdg";
            obj[0][1]="grg";
            obj[1][0]="rdgr";
            obj[1][1]="rgrg";
        }
        return obj;
    }

}
