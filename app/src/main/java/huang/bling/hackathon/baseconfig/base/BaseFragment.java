/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package huang.bling.hackathon.baseconfig.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by 沈东 on 2016/11/9.
 */
public class BaseFragment extends Fragment implements BaseFuncIml {

    private View mContentView;

    private ViewGroup container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        initView();
        initListener();
        initLoad();
        this.container = container;
        return mContentView;
    }

    public void setContentView(int viewId) {
        this.mContentView = getActivity().getLayoutInflater().inflate(viewId, container, false);
    }

    public View getContentView() {
        return mContentView;
    }

    protected void showShortToast(String pMsg) {
        Toast.makeText(getActivity(), pMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initLoad() {

    }

    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(getActivity(), toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class<? extends BaseActivity> toActivity,int flag){
        openActivityForResult(toActivity,null,flag);
    }

    protected void openActivityForResult(Class<? extends BaseActivity> toActivity,Bundle parameter,int flag){
        Intent intent = new Intent(getContext(),toActivity);
        if(parameter != null){
            intent.putExtras(parameter);
        }
        startActivityForResult(intent,flag);
    }
}
