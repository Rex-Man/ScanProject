package com.oocl.manre.fragmentstudy;

import android.Manifest;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;


import com.oocl.manre.fragmentstudy.fragment.HomeFragment;
import com.oocl.manre.fragmentstudy.fragment.ScanFragment;
import com.oocl.manre.fragmentstudy.fragment.SettingFragment;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private Fragment homeFragment,scanFragment,settingFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hideFragment(transaction);
                    homeFragment = new HomeFragment();
                    transaction.replace(R.id.fl_content, homeFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_scan:
                    hideFragment(transaction);
                    scanFragment = new ScanFragment();
                    transaction.replace(R.id.fl_content, scanFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_setting:
                    hideFragment(transaction);
                    settingFragment = new SettingFragment();
                    transaction.replace(R.id.fl_content, settingFragment);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {


        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 加入竖屏要处理的代码
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        ShowFragment(transaction);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    };

    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {

            transaction.remove(homeFragment);
        }
        if (scanFragment != null) {

            transaction.remove(scanFragment);
        }
        if (settingFragment != null) {

            transaction.remove(settingFragment);
        }
    }
    private void ShowFragment(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.replace(R.id.fl_content, homeFragment);
        }else if (scanFragment != null) {
            transaction.replace(R.id.fl_content, scanFragment);
        }else if (settingFragment != null) {
            transaction.replace(R.id.fl_content, settingFragment);
        }else{
            homeFragment=new HomeFragment();
            transaction.replace(R.id.fl_content, homeFragment);
        }
        transaction.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

}
