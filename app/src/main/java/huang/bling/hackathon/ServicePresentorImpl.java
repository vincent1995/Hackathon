package huang.bling.hackathon;

import huang.bling.hackathon.AppContract;
import huang.bling.hackathon.ServiceAsync;

public class ServicePresentorImpl implements AppContract.ServicePresenter {
    private ServiceViewImpl myView;
    private ServiceAsync myAsync;
    ServicePresentorImpl(ServiceViewImpl view){
        myView=view;
    }
    @Override
    public void queryCurrentTsunamiInfo() {
        myAsync=new ServiceAsync();
        myAsync.execute(myView);

    }
}
