package huang.bling.hackathon;
public class Presenter implements Contract.Presenter{
    Contract.View view;
    Contract.Module module;
    Presenter(Contract.View view,Contract.Module module){
        this.view = view;
        this.module = module;
    }
    @Override
    public void clickButton(String buttonStr) {
        String old_str=view.getOldString();
        String result = module.calculate(old_str,buttonStr);
        view.showAnswer(result);
    }
}