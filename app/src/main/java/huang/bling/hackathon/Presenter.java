package huang.bling.hackathon;

public class Presenter implements Contract.Presenter{
    Contract.View view;
    Contract.Module module;
    Presenter(Contract.View view,Contract.Module module){
        this.view = view;
        this.module = module;
    }
    @Override
    public void clickButton() {
        String init_value = view.getValue();
        int n = module.addNubmer(Integer.valueOf(init_value));
        view.showText(String.valueOf(n));
    }
}
