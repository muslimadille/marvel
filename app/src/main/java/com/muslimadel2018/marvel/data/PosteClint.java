package com.muslimadel2018.marvel.data;

import com.muslimadel2018.marvel.R;
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

    final String hash= "4a21ff2f249a26f5f9b649c9bb65b283";

    private static final String BASE_URL = "http://gateway.marvel.com/v1/public/";
    private PostsInterface postInterface;
    private static PosteClint INISTANCE;

    public PosteClint() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getUnSafeOkHttpClint().build())
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

    public Call<Response> getPosts() {
        return postInterface.getResponse("1","e85d6da09c704c0c24b6ecf818a9875b",hash);
    }


   /* public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
*/

   public static OkHttpClient.Builder getUnSafeOkHttpClint(){
       try {
           // Create a trust manager that does not validate certificate chains
           final TrustManager[] trustAllCerts = new TrustManager[]{
                   new X509TrustManager() {
                       @Override
                       public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                       }

                       @Override
                       public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                       }

                       @Override
                       public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                           return new java.security.cert.X509Certificate[]{};
                       }
                   }
           };

           // Install the all-trusting trust manager
           final SSLContext sslContext = SSLContext.getInstance("SSL");
           sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

           // Create an ssl socket factory with our all-trusting manager
           final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

           OkHttpClient.Builder builder = new OkHttpClient.Builder();
           builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
           builder.hostnameVerifier(new HostnameVerifier() {
               @Override
               public boolean verify(String hostname, SSLSession session) {
                   return true;
               }
           });
           return builder;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
   }

