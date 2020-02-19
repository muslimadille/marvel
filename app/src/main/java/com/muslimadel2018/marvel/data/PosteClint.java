package com.muslimadel2018.marvel.data;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.constants.ApiConstants;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.pojo.Response;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PosteClint {

    private PostsInterface postInterface;
    private static PosteClint INISTANCE;


    public PosteClint() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostsInterface.class);

    }

    public static PosteClint getINISTANCE() {
        if (null == INISTANCE) {
            INISTANCE = new PosteClint();
        }
        return INISTANCE;
    }

    public Call<Response> getPosts(String searchkey) {
        return postInterface.getResponse(searchkey, "1",ApiConstants.API_KEY , ApiConstants.HASH, 100, 0);
    }

}

