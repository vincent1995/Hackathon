package huang.bling.hackathon;

public interface Contract {
    interface View{
        public void showAnswer(String s);
        public String getOldString();
    }
    interface Module{
        public String calculate(String oldString,String buttonString);
    }
    interface Presenter{
        public void clickButton(String buttonString);
    }
}
