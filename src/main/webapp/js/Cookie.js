var Cookie = {
    /**
     * 读cookie操作
     * @param name 参数：cookie名称
     * @return String 返回值：字符串
     */
    get: function(sName) {
        var sRE = "(?:; )?" + sName + "=([^;]*);?";
        var oRE = new RegExp(sRE);
        if (oRE.test(document.cookie)) {
            return decodeURIComponent(RegExp["$1"]);
        } else {
            return null;
        }
    },
    /**
     * 写cookie操作
     * @param name  参数：cookie名称
     * @param value  参数：cookie值
     * @param oExpires  参数：过期时间
     * @return 返回值：无
     */
    set: function(sName, sValue, oExpires, sPath, sDomain, bSecure) {
        var sCookie = sName + "=" + encodeURIComponent(sValue);
        if (oExpires) {
            var sst = oExpires * 24 * 60 * 60 * 1000;
            date = new Date();
            date.setTime(date.getTime() + sst);
            sCookie += "; expires=" + date.toGMTString();
        }
        if (sPath) {
            sCookie += "; path=" + sPath;
        }
        if (sDomain) {
            sCookie += "; domain=" + sDomain;
        }
        if (bSecure) {
            sCookie += "; secure";
        }
        document.cookie = sCookie;
    },
    /**
     * 删除cookie操作
     * @param name  参数：cookie名称
     */
    del: function(name) { // 为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
        var date = new Date();
        date.setTime(date.getTime() - 10000);
        document.cookie = name + "=a; expires=" + date.toGMTString();
    }
};