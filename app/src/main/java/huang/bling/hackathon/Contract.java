package huang.bling.hackathon;

public interface Contract {
    interface Module{
        public int addNubmer(int num);
    }
    interface View{
        public void showText(String s);
        public String getValue();
    }
    interface Presenter{
        public void clickButton();
    }
}
