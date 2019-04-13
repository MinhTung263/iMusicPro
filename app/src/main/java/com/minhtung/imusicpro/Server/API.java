package com.minhtung.imusicpro.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minhtung.imusicpro.Model.Danhsachbaihat;
import com.minhtung.imusicpro.Model.TheLoai;
import com.minhtung.imusicpro.Model.Top100;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class API {
    public static Retrofit initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://whis263.000webhostapp.com/iMusicPro/FilePHP/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public interface ApiInterface {
        @POST("Danhsachnhac.php")
        public Call<List<Danhsachbaihat>> getDanhsachbh();

        @GET("Theloai.php")
        public Call<List<TheLoai>> getTheLoai();

        @GET("Top100.php")
        public Call<List<Top100>> getTop100();

        @FormUrlEncoded
        @POST("TimKiem.php")
        Call<List<Danhsachbaihat>>getTimKiemBaiHat(@Field("tukhoa") String tukhoa);

        @FormUrlEncoded
        @POST("Danhsachnhac.php")
        public Call<List<Danhsachbaihat>> getIDTheLoai(@Field("idTheLoai") String idTheLoai);
    }
}
