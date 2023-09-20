package methodsRepo;

import basePackage.Base;

import objectRepo.HomePageObjects;


public class HomePageMethods extends Base {
    private HomePageObjects obj;
    public HomePageMethods(){
        obj = new HomePageObjects();
    }


    public void toogleButton(){
        explicitClick(obj.getLogin_btn());
        //explicitClick(obj.getTbutton());

    }
}
