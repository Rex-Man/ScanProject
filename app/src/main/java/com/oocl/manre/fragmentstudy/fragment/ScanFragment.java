package com.oocl.manre.fragmentstudy.fragment;



import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oocl.manre.fragmentstudy.R;
import com.oocl.manre.fragmentstudy.entity.ResponseEntity;
import com.oocl.manre.fragmentstudy.entity.Scan;
import com.oocl.manre.fragmentstudy.retrofitinterface.ScanInterface;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScanFragment extends Fragment implements QRCodeView.Delegate {
    private static final String TAG = ScanFragment.class.getSimpleName();


    private QRCodeView mQRCodeView;
    public ScanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_scan, container, false);

        mQRCodeView = (ZXingView) view.findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);


        return view;


    }
    @Override
    public void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }

    @Override
    public void onStop() {
        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mQRCodeView.onDestroy();
        mQRCodeView.stopSpot();
        super.onDestroy();
    }

    public void vibrate() {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        if(result!=null && !"".equals(result)) {
            checkInDBIfNotHaveThenSaveInDB(result);
        }
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    private void checkInDBIfNotHaveThenSaveInDB(String scanResult)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://146.222.94.224:8080/scan/scanController/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            ScanInterface service = retrofit.create(ScanInterface.class);
             Scan scan= new Scan();
             scan.setScanCode(scanResult);
            Call<ResponseEntity<String>> call = service.saveScan(scan);
        call.enqueue(new Callback<ResponseEntity<String>>() {
            @Override
            public void onResponse(Call<ResponseEntity<String>> call, Response<ResponseEntity<String>> response) {
                if(response.body().getData()!=null && "success".equals(response.body().getData()))
                {
                    Log.i("zfq", "save successful");
                }else{
                    Log.i("zfq", "save fail");
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity<String>> call, Throwable t) {
                Log.i("zfq", t.getMessage());
            }
        });

    }

}
