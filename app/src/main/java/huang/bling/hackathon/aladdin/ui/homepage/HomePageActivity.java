package huang.bling.hackathon.aladdin.ui.homepage;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.sunlandgroup.aladdin.R;
import com.sunlandgroup.aladdin.baseconfig.base.BaseFrameActivity;
import com.sunlandgroup.aladdin.ui.homepage.HomePageContract;
import com.sunlandgroup.aladdin.ui.homepage.HomePageMoudle;
import com.sunlandgroup.aladdin.ui.homepage.HomePagePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by 沈东 on 2017/7/11.
 */

public class HomePageActivity extends BaseFrameActivity<HomePagePresenter, HomePageMoudle> implements HomePageContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void initView() {
        super.initView();
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

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
    public void onRequestError(String msg) {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onInternetError() {

    }








}
