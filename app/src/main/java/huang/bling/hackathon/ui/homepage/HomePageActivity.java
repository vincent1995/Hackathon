package huang.bling.hackathon.ui.homepage;

import android.os.Bundle;

import butterknife.ButterKnife;
import huang.bling.hackathon.R;
import huang.bling.hackathon.baseconfig.base.BaseFrameActivity;

/**
 * Created by 沈东 on 2017/7/11.
 */

public class HomePageActivity extends BaseFrameActivity<HomePagePresenter, HomePageMoudle> implements HomePageContract.View{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void initView() {
        super.initView();


    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onRequestError(String msg) {

    }



    @Override
    public void onInternetError() {

    }








}
