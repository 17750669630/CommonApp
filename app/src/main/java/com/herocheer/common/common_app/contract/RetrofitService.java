package com.herocheer.common.common_app.contract;

import com.herocheer.common.common_app.model.ResultCode;
import com.herocheer.common.common_app.model.ZhiHuBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @time:2018/4/9 at 9:46
 * @description: api配在这里
 */
public interface RetrofitService {

    String BASE_URL = "https://news-at.zhihu.com/api/4/";
    String BASE_URL2 = "http://192.168.0.105:8080/AndroidTest/";
    String BASE_URL3 = "http://www.qulishi.com/";

    /**
     * 测试接口
     *
     * @return
     */

    @GET("news/latest")
    Observable<ZhiHuBean> test();

    //获取验证码
    //使用@Url的时候@GET()括号内不能包含内容
    @GET("IdentifyingCode")
    Observable<ResultCode> getIdentifyCode();

    //登陆
    //使用@Url的时候@GET()括号内不能包含内容
    @GET("Login")
    Observable<ResultCode> getLogin(@Query("something") String something);

    //注册
    //使用@Url的时候@GET()括号内不能包含内容
    @GET("Register")
    Observable<ResultCode> getRegister(@Query("something")String something);

    //人物首页
    @GET("renwu")
    Observable<ResponseBody> getRenwu();

    //具体的某个人物
    @GET("renwu/{person}")
    Observable<ResponseBody> getRenwuOne(@Path("person") String person);

    //具体的某个字母开头的列表
    @GET("renwu/{char}")
    Observable<ResponseBody> getRenwuChar(@Path("char") String s);

    //具体的某个字母开头的列表
    @GET("{wenzhang}") //http://www.qulishi.com/article/201804/280396.html
    Observable<ResponseBody> getRenwuWenzhang(@Path("wenzhang") String s);
}
