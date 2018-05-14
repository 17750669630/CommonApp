package com.herocheer.common.common_app.presenter;

import com.herocheer.common.common_app.contract.QulishiContract;
import com.herocheer.common.common_app.model.QulishiBean;
import com.herocheer.common.common_app.model.QulishiPersonBean;
import com.herocheer.common.common_mvp.tool.LogUtil;
import com.herocheer.common.common_mvp.tool.RxManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @time:2018/4/10 at 14:39
 * @description: 趣历史网站捕捉P
 */
public class QulishiP extends QulishiContract.QuPresenter {

    //获取首页上的人物
    @Override
    public void requestRenwu() {
        RxManager.add(mModel.getRenwu().subscribe(responseBody -> {
            try {
                ArrayList<QulishiBean> beans;
                Document parse = Jsoup.parse(responseBody.string());
                Elements select = parse.select("div.strasegy_name");
                select = select.select("a[href]");
                beans = new ArrayList<>();
                for (Element e : select) {
                    beans.add(new QulishiBean(e.text(), e.attr("href")));
                    LogUtil.e(e.text() + e.attr("href"));
                }
                mView.returnRenwu(beans);
            } catch (IOException | StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }, Throwable -> mView.showError(Throwable.toString())));
    }

    //获取人物资料和图片
    @Override
    public void requestRenwuPerson(String s) {
        RxManager.add(mModel.getRenwuPerson(s).subscribe(responseBody -> {
            try {
                //左边的资料
                Document parse = Jsoup.parse(responseBody.string());
                Elements select1 = parse.select("div.ziliao p");
                String string1 = "";
                for (Element element : select1)
                    if (element.text().trim().length() > 0)
                        string1 += (element.text() + "\n");
                string1 = string1.substring(0, string1.length() - 1);

                //ziliao.add(element.text());


                String string2 = "";
                //中间的介绍
                List<String> jieshao = new ArrayList<>();
                Elements select2 = parse.select("div.w669");
                select2 = select2.select("p:not(p.initials)");
                select2 = select2.select("p:not(p:has(script))");
                select2 = select2.select("p:not(p[style])");
                for (Element element : select2)
                    if (element.text().trim().length() > 0)
                        string2 += (element.text() + "\n");
                string2 = string2.substring(0, string2.length() - 1);
                //jieshao.add(element.text());

                //图片的链接
                String imgUrl;
                Elements select3 = parse.select("div.bigpic");
                select3 = select3.select("img");
                imgUrl = select3.get(0).attr("src");


                //文章列表 set 排除重复对象需要重新写equal和hashcode
                Set<QulishiPersonBean.Wenzhang> wenzhangs = new HashSet<>();
                Elements select4 = parse.select("ul.newslist");
                select4 = select4.select("li");
                QulishiPersonBean bean = new QulishiPersonBean();
                for (Element element : select4) {
                    wenzhangs.add(bean.new Wenzhang(element.text().trim(), element.select("a").attr("href").trim()));
                }
                List<QulishiPersonBean.Wenzhang> wenzhang = new ArrayList(wenzhangs);
                mView.returnRenwuPerson(new QulishiPersonBean(string1, string2, imgUrl, wenzhang));
            } catch (IOException | StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }, Throwable -> mView.showError(Throwable.toString())));
    }

    //获取某字母开头的人物列表
    @Override
    public void requestRenwuCharList(String s) {
        RxManager.add(mModel.getRenwuCharList(s).subscribe(responseBody -> {
            try {
                ArrayList<QulishiBean> beans;
                Document parse = Jsoup.parse(responseBody.string());
                Elements select = parse.select("div.strasegy_name");
                select = select.select("a[href]");
                beans = new ArrayList<>();
                for (Element e : select)
                    beans.add(new QulishiBean(e.text(), e.attr("href")));
                mView.returnRenwuPersonList(beans);
            } catch (IOException | StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }, Throwable -> mView.showError(Throwable.toString())));
    }

    //获取某人物的文章
    @Override
    public void requestRenwuWenzhang(String s) {
        RxManager.add(mModel.getRenwuWenzhang(s).subscribe(responseBody -> {
            try {
                Document parse = Jsoup.parse(responseBody.string());
                Elements select = parse.select("div.n18_art_con");
                select = select.select("p");
                String s1 = "";
                for (Element element : select) {
                    if (element.text().replaceAll("　| ", "").length() > 0 && !element.text().trim().equals("网络配图"))
                        //if (s.contains("news"))
                            s1 += ("　　" + element.text().replaceAll("　| ", "") + "\n");
                        //else s1 += (element.text().trim() + "\n");
                }
                s1 = s1.substring(0, s1.length() - 1);
                mView.returnRenwuWenzhang(s1);

            } catch (IOException | StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }, Throwable -> mView.showError(Throwable.toString())));
    }

    @Override
    public void onStart() {

    }
}
