package com.ycl.car;

/**
 * 常量，数据接口
 * Created by y11621546 on 2017/1/17.
 */

public class ContantValue {
    private static final String ip = MyApp.getInstance().getSharedPreferences().getString("ip", "60.28.240.35:6300");
//    private static final String ip = MyApp.getInstance().getSharedPreferences().getString("ip", "60.28.240.35:6300");

    public static final String BASE_URL = "http://60.28.240.35:6300/";
    //    天气接口
    public static final String WEATHER = "http://apis.baidu.com/heweather/weather/free";
    //    public static final String WEATHER = "https://free-api.heweather.com/v5/";
    public static final String APIKEY = "eeb745dcca57aec9004b969ef2e4994f";
    public static final String DEFAULT_VALUE = "中汽工程";
    public static final String TOOLBAR_TITLE = "toolbar_title";
    public static final String TOOLBAR_RIGHT = "toolbar_right";
    public static final String TOOLBAR_LEFT = "toolbar_left";
    //    新闻
    public static final String NEWS_URL = "padApi/News/Index";
    //    帖子
    public static final String BULLETIN_URL = "padApi/Community/GetBulletin";
    //    登录
    public static final String LOGIN_URL = "padApi/Account/login";
    //    账号资料
    public static final String UPDATE_USER_INFO_URL = "padApi/Account/UpdateInfo";
    //    实时报警
    public static final String GET_ALARM_REAL_TIME_URL = "padApi/Alrm/GetAlrmRealTime";
    //    更改密码
    public static final String UPDATE_PWD_URL = "padApi/Account/UpdatePassword";
    //    滚床
    public static final String DIAGRAN_ITEMS_URL = "padApi/Diagrame/DiagramItems";
    //    维修资料
    public static final String MAINTAIN_DATA = "padApi/ZqEquipDocument/ListsDoc";
    //    智能备件库
    public static final String COMPARE_PART_LIBRARY = "padApi/ZqEquipSp/ListSp";
    //    智能备件--设备详情
    public static final String COMPARE_PART = "padApi/ZqEquipSp/ListEquipSp";

    //PM预防性维修计划
    public static final String PM_MAINTAAIN_PLAN = "padApi/ZqEquipPM/lists";
    //TPM点检
    public static final String TPM_CHECK = "padApi/ZqEquipTPM/Lists";
    //PM记录
    public static final String PM_LIST_PLAN = "padApi/ZqEquipPM/listsplan";
    //TPM记录
    public static final String TPM_LIST_PLAN = "padApi/ZqEquipTPM/listsplan";
    //上传并获取图片
    public static final String GET_FILE = "padApi/FilesOpera/GetFiles";
    //PM保存信息
    public static final String SAVE_PM_INFO = "padApi/ZqEquipPM/SaveInfo";
    //TPM保存信息
    public static final String SAVE_TPM_INFO = "padApi/ZqEquipTPM/SaveInfo";
    //设备信息
    public static final String EQ_INFO = "padApi/ZqEquip/Info";
    //维修信息--设备详情
    public static final String ZqEquipRepair = "padApi/ZqEquipRepair/lists";
    //保存维修信息
    public static final String ZqEquipRepairSaveInfo = "padApi/ZqEquipRepair/SaveInfo";
    //获取设备管理下的类别
    public static final String GetMenuByParent = "padApi/Account/GetMenuByParent";
    //获取设备管理下的类别下
    public static final String DiagramItemsForLoop = "padApi/Diagrame/DiagramItemsForLoop";
    //点值
    public static final String DHPointValues2 = "ZQAPI/pointValue/DHPointValues2";
    //    天气
    public static final String getWeather = "PadApi/Weather/Index";
    //    获取通知数量
    public static final String getNotifiNum = "PadApi/ZqEquipRepair/GetIndexCount";
    //   获取指定范围需要执行的PM
    public static final String getPMListTodoList = "PadApi/ZqEquipPM/ListTodoList";
    //   获取指定范围需要执行的TPM
    public static final String getTPMListTodoList = "PadApi/ZqEquipTPM/ListTodoList";
    //   获取历史预警信息
    public static final String getAlrmHistory = "PadApi/Alrm/GetAlrmHistory";
    //获取推送消息
    public static final String getPushList = "PadApi/PushInfo/lists";
    //获取通知消息
    public static final String getMessageList = "PadApi/Message/List";
    //查看推送消息
    public static final String getPushDetailInfo = "PadApi/PushInfo/detail";
    //    查看通知详情
    public static final String getMessageDetailInfo = "PadApi/Message/Detail";
    //获取维修信息
    public static final String getRepairInfo = "PadApi/ZqEquipRepair/GetInfo";
    //获取维修信息列表
    public static final String getRepairList = "PadApi/ZqEquipRepair/lists";
    //修改维修信息
    public static final String saveRepairInfo = "PadApi/ZqEquipRepair/SaveInfo";
    //获取台账信息
    public static final String getLedgerInfo = "PadApi/ZqEquip/YListsEq";
    //校验
    public static final String checkUrl = "PadApi/CheckApi/dc";
    //多媒体控制
    public static final String mediaUrl = "padApi/Multimedia/List";


}
