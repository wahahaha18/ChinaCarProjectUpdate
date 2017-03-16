package com.ycl.car.utils;

import com.ycl.car.ContantValue;
import com.ycl.car.model.LoginResponse;
import com.ycl.car.model.MainNews;
import com.ycl.car.model.Post;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 请求网络获取数据
 * Created by y11621546 on 2017/1/21.
 */
public interface HttpService {
    //    展示要闻
    @FormUrlEncoded
    @POST(ContantValue.NEWS_URL)
    Observable<MainNews> showMainNews(@Field("menu_id") String menu_id);

    //   最新帖子
    @FormUrlEncoded
    @POST(ContantValue.BULLETIN_URL)
    Observable<Post> showFreshBulletin(@Field("is_fresh") String fresh);

    //   热门帖子
    @FormUrlEncoded
    @POST(ContantValue.BULLETIN_URL)
    Observable<Post> showHotBulletin(@Field("is_hot") String hot);

    //   精华帖子
    @FormUrlEncoded
    @POST(ContantValue.BULLETIN_URL)
    Observable<Post> showTypicalBulletin(@Field("is_typical") String typical);

    //   登录
    @FormUrlEncoded
    @POST(ContantValue.LOGIN_URL)
    Observable<LoginResponse> login(@Field("u_name") String name, @Field("u_pwd") String pwd);

    //   登录
    @FormUrlEncoded
    @POST(ContantValue.LOGIN_URL)
    Observable<ResponseBody> login1(@Field("u_name") String name, @Field("u_pwd") String pwd);

    //   更改密码
    @FormUrlEncoded
    @POST(ContantValue.UPDATE_PWD_URL)
    Observable<ResponseBody> modifyPwd(@Field("id") String id, @Field("u_pwd") String pwd, @Field("NewPassword ") String NewPassword);

    //   天气
    @FormUrlEncoded
    @POST(ContantValue.WEATHER)
    Observable<ResponseBody> getWeather(@Header("apikey") String apikey, @Field("city") String city);  //   天气

    //  更新用户信息
    @FormUrlEncoded
    @POST(ContantValue.UPDATE_USER_INFO_URL)
    Observable<ResponseBody> updateUserInfo(@Field("id") String id, @Field("company_") String company, @Field("alias_") String alias, @Field("gender_") String gender);

    //  实时报警信息
    @FormUrlEncoded
    @POST(ContantValue.GET_ALARM_REAL_TIME_URL)
    Observable<ResponseBody> getAlarmRealTimeInfo(@Field("uid") String uid, @Field("level_id") String level_id, @Field("zone_id") String zone_id);

    //  设备维修资料
    @FormUrlEncoded
    @POST(ContantValue.MAINTAIN_DATA)
    Observable<ResponseBody> getMaintainEquimentInfo(@Field("type") String type, @Field("eqno") String eqno, @Field("dname") String dname);


    //  备件维修资料
    @FormUrlEncoded
    @POST(ContantValue.MAINTAIN_DATA)
    Observable<ResponseBody> getMaintainSpareInfo(@Field("type") String type, @Field("spno") String eqno, @Field("dname") String dname);

    //  智能备件库
    @FormUrlEncoded
    @POST(ContantValue.COMPARE_PART_LIBRARY)
    Observable<ResponseBody> getSparePartLibraryInfo(@Field("spno") String eqno, @Field("spname") String dname);

    //  PM预防性维修计划
    @FormUrlEncoded
    @POST(ContantValue.PM_MAINTAAIN_PLAN)
    Observable<ResponseBody> getPmMaintainPlan(@Field("uid") String uid, @Field("eqno") String eqno);

    //  TPM点检
    @FormUrlEncoded
    @POST(ContantValue.TPM_CHECK)
    Observable<ResponseBody> getTPM(@Field("uid") String uid, @Field("eqno") String eqno);

    //  PM记录
    @FormUrlEncoded
    @POST(ContantValue.PM_LIST_PLAN)
    Observable<ResponseBody> getPmListPlan(@Field("uid") String uid, @Field("pmid") String pmid);

    //  TPM记录
    @FormUrlEncoded
    @POST(ContantValue.TPM_LIST_PLAN)
    Observable<ResponseBody> getTPMListPlan(@Field("uid") String uid, @Field("tpmid") String pmid);

    //  附件--上传图片
    @Multipart
    @POST(ContantValue.GET_FILE)
    Observable<ResponseBody> getFile(@Query("id") String uid, @Part("file\";filename=\"file.png") RequestBody file);

    //  保存信息
    @FormUrlEncoded
    @POST(ContantValue.SAVE_PM_INFO)
    Observable<ResponseBody> savePmInfo(@Field("uid") String uid, @Field("pmid") String pmid, @Field("rcont") String rcont, @Field("imgs") String imgs);

    //  保存信息
    @FormUrlEncoded
    @POST(ContantValue.SAVE_TPM_INFO)
    Observable<ResponseBody> saveTpmInfo(@Field("uid") String uid, @Field("tpmid") String pmid, @Field("rcont") String rcont, @Field("imgs") String imgs);

    //   设备信息
    @FormUrlEncoded
    @POST(ContantValue.EQ_INFO)
    Observable<ResponseBody> getEqInfo(@Field("eqno") String eqno);

    //   智能备件---设备详情
    @FormUrlEncoded
    @POST(ContantValue.COMPARE_PART)
    Observable<ResponseBody> getEqPart(@Field("eqno") String eqno);

    //   维修信息---设备详情
    @FormUrlEncoded
    @POST(ContantValue.ZqEquipRepair)
    Observable<ResponseBody> getZqEquipRepair(@Field("eqno") String eqno, @Field("status") String status, @Field("uid") String uid);

    //   保存维修信息---设备详情
    @FormUrlEncoded
    @POST(ContantValue.ZqEquipRepairSaveInfo)
    Observable<ResponseBody> getZqEquipRepairSave(@Field("uid") String uid, @Field("rcont") String rcont, @Field("id") String id, @Field("imgs") String imgs, @Field("rstatus") String rstatus);

    //   设备管理下的类别
    @FormUrlEncoded
    @POST(ContantValue.GetMenuByParent)
    Observable<ResponseBody> getMenuByParent(@Field("sen") String sen, @Field("uid") String uid);

    //   设备管理下的类别下
    @FormUrlEncoded
    @POST(ContantValue.DiagramItemsForLoop)
    Observable<ResponseBody> diagramItemsForLoop(@Field("diagrame_name") String diagrame_name);

    //   设备管理下的类别下
    @FormUrlEncoded
    @POST(ContantValue.DHPointValues2)
    Observable<ResponseBody> getDHPointValues2(@Field("point_names") String diagrame_name);

    //   获取天气
    @POST(ContantValue.getWeather)
    Observable<ResponseBody> getWeather();

    //   获取通知数量
    @FormUrlEncoded
    @POST(ContantValue.getNotifiNum)
    Observable<ResponseBody> getNotificationNum(@Field("uid") String uid);

    //  获取指定范围需要执行的PM
    @FormUrlEncoded
    @POST(ContantValue.getPMListTodoList)
    Observable<ResponseBody> getPMListTodoList(@Field("uid") String uid);

    //  获取指定范围需要执行的TPM
    @FormUrlEncoded
    @POST(ContantValue.getTPMListTodoList)
    Observable<ResponseBody> getTPMListTodoList(@Field("uid") String uid);

    //  获取历史预警信息
    @FormUrlEncoded
    @POST(ContantValue.getAlrmHistory)
    Observable<ResponseBody> getAlrmHistory(@Field("uid") String uid,
                                            @Field("level_") String level_,
                                            @Field("zone_") String zone_,
                                            @Field("startm") String startm,
                                            @Field("endtm") String endtm
    );

    //  获取历史推送信息
    @FormUrlEncoded
    @POST(ContantValue.getPushList)
    Observable<ResponseBody> getPushList(@Field("uid") String uid);

    //  获取通知消息
    @FormUrlEncoded
    @POST(ContantValue.getMessageList)
    Observable<ResponseBody> getMessageList(@Field("uid") String uid);//  获取通知消息

    //获取通知详情
    @FormUrlEncoded
    @POST(ContantValue.getMessageDetailInfo)
    Observable<ResponseBody> getMessageDetailInfo(@Field("uid") String uid, @Field("id") String id);//  获取通知消息

    //获取消息详情
    @FormUrlEncoded
    @POST(ContantValue.getPushDetailInfo)
    Observable<ResponseBody> getPushDetailInfo(@Field("uid") String uid, @Field("id") String id);

    //  获取维修列表
    @FormUrlEncoded
    @POST(ContantValue.getRepairList)
    Observable<ResponseBody> getRepairList(@Field("uid") String uid, @Field("eqno") String eqno, @Field("status") String status, @Field("startm") String startm, @Field("endtm") String endtm);//  获取通知消息

    //获取通知详情
    @FormUrlEncoded
    @POST(ContantValue.getRepairInfo)
    Observable<ResponseBody> getRepairInfo(@Field("id") String id);//  获取通知消息

    //获取消息详情
    @FormUrlEncoded
    @POST(ContantValue.saveRepairInfo)
    Observable<ResponseBody> saveRepairInfo(@Field("id") String uid, @Field("rstatus") String rstatus, @Field("imgs") String imgs, @Field("rcont") String rcont);

    //获取台账信息
    @FormUrlEncoded
    @POST(ContantValue.getLedgerInfo)
    Observable<ResponseBody> getLedgerInfo(@Field("eqno") String eqno, @Field("eqtype_id") String eqtype_id, @Field("eqwksp_id") String eqwksp_id, @Field("eqsystem_id") String eqsystem_id);

    //    校验
    @POST(ContantValue.checkUrl)
    Observable<ResponseBody> checkUrl();

    //   获取媒体列表
    @FormUrlEncoded
    @POST(ContantValue.mediaUrl)
    Observable<ResponseBody> getMediaList(@Field("uid") String uid, @Field("type") String type);
}
