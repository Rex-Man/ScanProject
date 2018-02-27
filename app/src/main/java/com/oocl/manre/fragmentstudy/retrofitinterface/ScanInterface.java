package com.oocl.manre.fragmentstudy.retrofitinterface;

import com.oocl.manre.fragmentstudy.entity.Page;
import com.oocl.manre.fragmentstudy.entity.ResponseEntity;
import com.oocl.manre.fragmentstudy.entity.Scan;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by manre on 21/02/2018.
 */

public interface ScanInterface {
    @GET("getScanListPage")
    Call<ResponseEntity<Page<Scan>>> getAllScanListByPage(@Query("currentPage") Integer currentPage, @Query("pageSize") Integer pageSize);



    @POST("saveScan")
    Call<ResponseEntity<String>> saveScan(@Body Scan scan);
}
