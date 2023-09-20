package testCases;

import basePackage.Base;
import methodsRepo.HomePageMethods;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001 extends Base {
    private HomePageMethods fun;
    @BeforeClass
    public void init(){
        fun = new HomePageMethods();
    }

    @Test
    public void testToogleButton(){
       // HomePageMethods fun = new HomePageMethods();
        fun.toogleButton();
        System.out.println("Button clicked!!!");
    }
}
