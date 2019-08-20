seajs.config({
    base: "sea-modules/",
    path:{
//        nsts:'http://localhost:8080/ceg/js/nsts/coder/'
    },
//    preload: 'jquery/jquery/1.10.1/jquery-debug',
    alias: {
        "$": 'jquery/jquery/1.10.1/jquery-debug',
        "jquery": 'jquery/jquery/1.10.1/jquery-debug',
        "$-debug": 'jquery/jquery/1.10.1/jquery-debug',
        "jquery-debug": 'jquery/jquery/1.10.1/jquery-debug',
        "widget": 'arale/widget/1.1.1/widget',
        "tip": 'arale/tip/1.2.2/tip',
        "validator":"arale/validator/0.9.7/validator",
        "page":"nsts/page/1.0.1/page-debug",
        "calendar":"arale/calendar/1.0.0/calendar",
        "util":"nsts/util/1.0.1/util-debug",
        "qrcode":"arale/qrcode/1.0.3/qrcode",
        "tab":"arale/switchable/1.0.2/tabs",
        "urlencode":"nsts/url/1.0.1/urlencode",
        "url":"nsts/url/1.0.1/url-debug",
        "url2":"hotoo/url/1.1.0/url",//附带设置参数方法
        "confirmBox":"arale/dialog/1.3.0/confirmbox-debug",
        "dialog":"arale/dialog/1.3.0/dialog",
        "sticky":"arale/sticky/1.3.1/sticky",
        "topTool":"nsts/top/1.0.1/topTool",
        "index":"nsts/page/1.0.1/index"
    }
});
