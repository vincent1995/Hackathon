package huang.bling.hackathon.baseconfig.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huang.bling.hackathon.global.Constants;
import huang.bling.hackathon.util.AppManager;
import huang.bling.hackathon.util.NetworkUtil;

/**
 * Created by 沈东 on 2016/11/9.
 */

public class BaseActivity extends AppCompatActivity implements BaseFuncIml, View.OnClickListener {

    protected Fragment mCurrFragment;

    private int mFragmentId;

    private static final String TAG = "BaseActivity";

    private MaterialDialog.Builder mLoadingDialog;

    private long mExitTime;

    private boolean isExit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        if (!NetworkUtil.isNetworkAvailable(this)) {
            //   showShortToast("无网络，请检查网络!");
        }
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        initData();
        initListener();
        initLoad();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    public void setFrameId(int mFragmentId) {
        this.mFragmentId = mFragmentId;
    }

    protected void toFragment(Fragment toFragment) {
        if (mCurrFragment == null) {
            showShortToast("mCurrFragment is null!");
            return;
        }

        if (toFragment == null) {
            showShortToast("toFragment is null!");
            return;
        }

        if (toFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(mCurrFragment)
                    .show(toFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(mCurrFragment)
                    .add(mFragmentId, toFragment).show(toFragment)
                    .commit();
        }

        mCurrFragment = toFragment;

    }

    public Fragment getCurrFragment() {
        return mCurrFragment;
    }

    public void setCurrFragment(Fragment mCurrFragment) {
        this.mCurrFragment = mCurrFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        initLoadingDialog();
    }

    private void initLoadingDialog() {
        mLoadingDialog = new MaterialDialog.Builder(this);
        mLoadingDialog.progress(true, 0);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initLoad() {

    }

    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class<? extends BaseActivity> toActivity, int flag) {
        openActivityForResult(toActivity, null, flag);
    }

    protected void openActivityForResult(Class<? extends BaseActivity> toActivity, Bundle parameter, int flag) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivityForResult(intent, flag);
    }

    public MaterialDialog.Builder getLoadingDialog() {
        return mLoadingDialog;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    showShortToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    AppManager.getAppManager().finishAllActivity();
                    AppManager.getAppManager().AppExit(this);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    protected boolean isPermissionGranted(String permissionName, int questCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //判断是否需要请求允许权限
        int hasPermision = checkSelfPermission(permissionName);
        if (hasPermision != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{permissionName}, questCode);
            return false;
        }
        return true;
    }

    protected boolean isPermissionsAllGranted(String[] permArray, int questCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //获得批量请求但被禁止的权限列表
        List<String> deniedPerms = new ArrayList<String>();
        for (int i = 0; permArray != null && i < permArray.length; i++) {
            if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(permArray[i])) {
                deniedPerms.add(permArray[i]);
            }
        }
        //进行批量请求
        int denyPermNum = deniedPerms.size();
        if (denyPermNum != 0) {
            requestPermissions(deniedPerms.toArray(new String[denyPermNum]), questCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        }
        switch (requestCode) {
            case Constants.QUEST_CODE_LOCTION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    popAlterDialog("位置", "位置信息权限被禁止，将导致定位失败。。是否开启该权限？(步骤：应用信息->权限->'勾选'位置)");
                }
                break;
            case Constants.QUEST_CODE_CAMERA:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    popAlterDialog("相机", "摄像头使用权限被禁止，拍照无法正常使用。是否开启该权限？(步骤：应用信息->权限->'勾选'相机)");
                }
                break;
            case Constants.QUEST_CODE_SEND_SMS:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    popAlterDialog("短信", "发送短信权限被禁止，无法使用反馈/建议功能。是否开启该权限？(步骤：应用信息->权限->'勾选'短信)");
                }
                break;
            case Constants.QUEST_CODE_ALL:
                doPermissionAll(Constants.permArray, grantResults);
                break;
            case Constants.QUEST_CODE_CALL_PHONE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    popAlterDialog("拨打电话", "拨打电话权限被禁止，无法使用拨打电话功能。是否开启该权限？(步骤：应用信息->权限->'勾选'电话)");
                }
                break;
            case Constants.QUEST_CODE_EXTERNAL:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    popAlterDialog("存储文件", "存储文件权限被禁止，无法使用更新功能。是否开启该权限？(步骤：应用信息->权限->'勾选'电话)");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
                break;
        }
    }

    protected void doPermissionAll(String[] permissions, int[] grantResults) {
        int grantedPermNum = 0;
        int totalPermissons = permissions.length;
        int totalResults = grantResults.length;
        if (totalPermissons == 0 || totalResults == 0) {
            return;
        }
        Map<String, Integer> permResults = new HashMap<String, Integer>();
        //初始化Map容器，用于判断哪些权限被授予
        for (String perm : Constants.permArray) {
            permResults.put(perm, PackageManager.PERMISSION_DENIED);
        }
        //根据授权的数目和请求授权的数目是否相等来判断是否全部授予权限
        for (int i = 0; i < totalResults; i++) {
            permResults.put(permissions[i], grantResults[i]);
            if (permResults.get(permissions[i]) == PackageManager.PERMISSION_GRANTED) {
                grantedPermNum++;
            }
        }
        if (grantedPermNum == totalPermissons) {
            //用于授予全部权限
        } else {
//            showShortMsg( "批量申请权限失败，将会影响正常使用！");
        }
    }

    private void showShortMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void popAlterDialog(final String msgFlg, String msgInfo) {
        new AlertDialog.Builder(BaseActivity.this)
                .setTitle("使用警告")
                .setMessage(msgInfo)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //前往应用详情界面
                        try {
                            Uri packUri = Uri.parse("package:" + getPackageName());
                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packUri);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            BaseActivity.this.startActivity(intent);
                        } catch (Exception e) {
                            showShortMsg("跳转失败");
                        }
                        dialog.dismiss();
                    }
                }).create().show();
    }
}
