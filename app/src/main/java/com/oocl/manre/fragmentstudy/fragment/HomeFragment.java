package com.oocl.manre.fragmentstudy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oocl.manre.fragmentstudy.R;
import com.oocl.manre.fragmentstudy.adapter.ScanAdapter;
import com.oocl.manre.fragmentstudy.entity.Page;
import com.oocl.manre.fragmentstudy.entity.ResponseEntity;
import com.oocl.manre.fragmentstudy.entity.Scan;
import com.oocl.manre.fragmentstudy.retrofitinterface.ScanInterface;


import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Integer currentPage=0;
    private Integer pageSize=10;
    private List<Scan> showData=new ArrayList<Scan>();
    ScanAdapter mAdapter ;
    View view;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view=inflater.inflate(R.layout.fragment_home, container, false);
        RefreshLayout refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                showData.clear();
                currentPage=0;
                getData();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                currentPage++;
                getData();
                refreshLayout.finishLoadMore();
            }
        });
        mAdapter = new ScanAdapter(getActivity(), showData);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rvScanList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        getData();
        return view;
    }

    private  void getData(){
            Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://146.222.94.224:8080/scan/scanController/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ScanInterface service = retrofit.create(ScanInterface.class);
            Call<ResponseEntity<Page<Scan>>> call = service.getAllScanListByPage(currentPage,pageSize);
            call.enqueue(new Callback<ResponseEntity<Page<Scan>>>() {
                @Override
                public void onResponse(Call<ResponseEntity<Page<Scan>>> call, Response<ResponseEntity<Page<Scan>>> response) {
                    if(response.body().getData()!=null )
                    {
                        Page<Scan> value=response.body().getData();
                        showData.addAll(value.getList());
                        mAdapter.updataScan(getActivity(), showData);
                        Log.i("zfq", "successful");

                    }else{
                        Log.i("zfq", "fail");
                    }
                }

                @Override
                public void onFailure(Call<ResponseEntity<Page<Scan>>> call, Throwable t) {
                    Log.i("zfq", t.getMessage());
                }
            });
    }

}
