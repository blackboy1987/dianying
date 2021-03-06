package com.bootx.app.dianying.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Demo {

    /**
     * Code : 200
     * Data : {"app":"微信","info":"info","admin":"岁岁热剧","site":{"message":{"radio":"1","tmpIds":"m9Tnwq3Ep3gNgKlhV8HYc1xc4Xk_15_-NbwPmP2tbI4"},"sharepic":"","kefu":{"type":"1","url":"https://dl.yulu123.xyz/upload/vod/20201125-1/c33f4fbe36c815cacbfec5a826391350.jpeg"},"share":{"type":"0","wxText":""},"platform":"0","ip":{"radio":"1","text":"腾讯云"},"login":{"radio":"0","num":""}},"index":{"wxAdId":{"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8"},"tanchuang":{"radio":"0","type":"2","image":"","url":""},"share":{"title":"","image":""},"notice":"","more":"1"},"detail":{"play":{"radio":"0","APPID":""},"douban":"1","dbUpdate":"1","dbStills":"1","gg":{"radio":"0","type":"0","image":"","url":""},"wxAdId":{"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8","jlspId":""},"adMsg":{"start":"","close":""}},"feilei":{"wxAdId":{"ysId":"adunit-3a8a1b9ec6526a5c","cpId":"adunit-480cbd6aaa7ff814"},"type_class":"[{\r\n\t\"type_name\": \"连续剧\",\r\n\t\"type_id\": \"2\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"言情\", \"喜剧\", \"悬疑\", \"都市\", \"偶像\", \"古装\", \"军事\", \"警匪\", \"历史\", \"励志\", \"神话\", \"谍战\", \"青春\", \"家庭\", \"动作\", \"情景\", \"武侠\",\r\n\t\t\t\"科幻\", \"其他\"\r\n\t\t],\r\n\t\t\"area\": [\"全部\", \"大陆\", \"美国\", \"香港\", \"台湾\", \"韩国\", \"泰国\", \"日本\", \"英国\", \"新加坡\"],\r\n\t\t\"lang\": \"全部,国语,英语,粤语,闽南语,韩语,日语,其它\",\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\", \"2009\",\r\n\t\t\t\"2008\", \"2006\", \"2005\", \"2004\"\r\n\t\t]\r\n\t}\r\n}, {\r\n\t\"type_name\": \"电影\",\r\n\t\"type_id\": \"1\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"喜剧\", \"爱情\", \"动作\", \"恐怖\", \"科幻\", \"剧情\", \"犯罪\", \"奇幻\", \"战争\", \"悬疑\", \"动画\", \"文艺\", \"伦理\", \"纪录\", \"传记\", \"歌舞\", \"古装\",\r\n\t\t\t\"历史\", \"惊悚\", \"其他\"\r\n\t\t],\r\n\t\t\"area\": [\"全部\", \"大陆\", \"美国\", \"香港\", \"韩国\", \"日本\", \"法国\", \"英国\", \"德国\", \"台湾\", \"泰国\", \"印度\", \"其他\"],\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\"]\r\n\t}\r\n}, {\r\n\t\"type_name\": \"综艺\",\r\n\t\"type_id\": \"3\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"选秀\", \"情感\", \"访谈\", \"播报\", \"旅游\", \"音乐\", \"美食\", \"纪实\", \"曲艺\", \"生活\", \"游戏互动\", \"财经\", \"求职\", \"其他\"],\r\n\t\t\"area\": [\"全部\", \"内地\", \"港台\", \"日韩\", \"欧美\", \"其他\"],\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\"]\r\n\t}\r\n}, {\r\n\t\"type_name\": \"动漫\",\r\n\t\"type_id\": \"4\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"热血\", \"科幻\", \"美少女\", \"魔幻\", \"经典\", \"励志\", \"少儿\", \"冒险\", \"搞笑\", \"推理\", \"恋爱\", \"治愈\", \"幻想\", \"校园\", \"动物\", \"机战\",\r\n\t\t\t\"亲子\", \"儿歌\", \"运动\", \"悬疑\", \"怪物\", \"战争\", \"益智\", \"青春\", \"童话\", \"竞技\", \"动作\", \"社会\", \"友情\", \"真人版\", \"电影版\", \"OVA版\", \"TV版\", \"新番动画\",\r\n\t\t\t\"完结动画\"\r\n\t\t],\r\n\t\t\"area\": [\"全部\", \"日本\", \"美国\", \"大陆\"],\r\n\t\t\"lang\": \"全部,国语,英语,粤语,闽南语,韩语,日语,其它\",\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\", \"2009\",\r\n\t\t\t\"2008\", \"2007\", \"2006\", \"2005\", \"2004\"\r\n\t\t]\r\n\t}\r\n}]"},"topic":{"wxAdId":{"ysId":"adunit-e9211ec2715058f8","cpId":"adunit-480cbd6aaa7ff814"},"tags":""},"play":{"isPoints":"1","enforce":"0","second":"","num":"","interval":0,"adMsg":{"start":"","close":""},"gg":{"radio":"0","type":"0","image":"","url":""},"wxAdId":{"jlspId":"adunit-c5ecca0f90e19f5a","cpId":"adunit-480cbd6aaa7ff814","spqtId":"adunit-4077ea75079b2394","ysId":"adunit-e9211ec2715058f8"},"vodPlayer":{"id":["qq","qiyi","youku","mgtv","dbm3u8","bjm3u8","tkm3u8","zuidam3u8"],"title":["tx线路","qy线路","yk线路","mg线路","db线路","bj线路","tk线路","zd线路"]}},"wode":{"gg":{"radio":"0","type":"2","image":"https://dl.yulu123.xyz/upload/vod/20201125-1/b0e8f9992929a88a39f6fad80b23d9c4.png","url":""},"statement":"","wxAdId":{"ysId":"adunit-e9211ec2715058f8"},"more":{"radio":"0"}},"other":{"wxAdId":{"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8"}},"wxverify":true,"check":"get"}
     * msg :
     */

    @JsonProperty("Code")
    private Integer Code;
    @JsonProperty("Data")
    private DataDTO Data;
    @JsonProperty("msg")
    private String msg;

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public DataDTO getData() {
        return Data;
    }

    public void setData(DataDTO data) {
        Data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataDTO {
        /**
         * app : 微信
         * info : info
         * admin : 岁岁热剧
         * site : {"message":{"radio":"1","tmpIds":"m9Tnwq3Ep3gNgKlhV8HYc1xc4Xk_15_-NbwPmP2tbI4"},"sharepic":"","kefu":{"type":"1","url":"https://dl.yulu123.xyz/upload/vod/20201125-1/c33f4fbe36c815cacbfec5a826391350.jpeg"},"share":{"type":"0","wxText":""},"platform":"0","ip":{"radio":"1","text":"腾讯云"},"login":{"radio":"0","num":""}}
         * index : {"wxAdId":{"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8"},"tanchuang":{"radio":"0","type":"2","image":"","url":""},"share":{"title":"","image":""},"notice":"","more":"1"}
         * detail : {"play":{"radio":"0","APPID":""},"douban":"1","dbUpdate":"1","dbStills":"1","gg":{"radio":"0","type":"0","image":"","url":""},"wxAdId":{"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8","jlspId":""},"adMsg":{"start":"","close":""}}
         * feilei : {"wxAdId":{"ysId":"adunit-3a8a1b9ec6526a5c","cpId":"adunit-480cbd6aaa7ff814"},"type_class":"[{\r\n\t\"type_name\": \"连续剧\",\r\n\t\"type_id\": \"2\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"言情\", \"喜剧\", \"悬疑\", \"都市\", \"偶像\", \"古装\", \"军事\", \"警匪\", \"历史\", \"励志\", \"神话\", \"谍战\", \"青春\", \"家庭\", \"动作\", \"情景\", \"武侠\",\r\n\t\t\t\"科幻\", \"其他\"\r\n\t\t],\r\n\t\t\"area\": [\"全部\", \"大陆\", \"美国\", \"香港\", \"台湾\", \"韩国\", \"泰国\", \"日本\", \"英国\", \"新加坡\"],\r\n\t\t\"lang\": \"全部,国语,英语,粤语,闽南语,韩语,日语,其它\",\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\", \"2009\",\r\n\t\t\t\"2008\", \"2006\", \"2005\", \"2004\"\r\n\t\t]\r\n\t}\r\n}, {\r\n\t\"type_name\": \"电影\",\r\n\t\"type_id\": \"1\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"喜剧\", \"爱情\", \"动作\", \"恐怖\", \"科幻\", \"剧情\", \"犯罪\", \"奇幻\", \"战争\", \"悬疑\", \"动画\", \"文艺\", \"伦理\", \"纪录\", \"传记\", \"歌舞\", \"古装\",\r\n\t\t\t\"历史\", \"惊悚\", \"其他\"\r\n\t\t],\r\n\t\t\"area\": [\"全部\", \"大陆\", \"美国\", \"香港\", \"韩国\", \"日本\", \"法国\", \"英国\", \"德国\", \"台湾\", \"泰国\", \"印度\", \"其他\"],\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\"]\r\n\t}\r\n}, {\r\n\t\"type_name\": \"综艺\",\r\n\t\"type_id\": \"3\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"选秀\", \"情感\", \"访谈\", \"播报\", \"旅游\", \"音乐\", \"美食\", \"纪实\", \"曲艺\", \"生活\", \"游戏互动\", \"财经\", \"求职\", \"其他\"],\r\n\t\t\"area\": [\"全部\", \"内地\", \"港台\", \"日韩\", \"欧美\", \"其他\"],\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\"]\r\n\t}\r\n}, {\r\n\t\"type_name\": \"动漫\",\r\n\t\"type_id\": \"4\",\r\n\t\"type_extend\": {\r\n\t\t\"class\": [\"全部\", \"热血\", \"科幻\", \"美少女\", \"魔幻\", \"经典\", \"励志\", \"少儿\", \"冒险\", \"搞笑\", \"推理\", \"恋爱\", \"治愈\", \"幻想\", \"校园\", \"动物\", \"机战\",\r\n\t\t\t\"亲子\", \"儿歌\", \"运动\", \"悬疑\", \"怪物\", \"战争\", \"益智\", \"青春\", \"童话\", \"竞技\", \"动作\", \"社会\", \"友情\", \"真人版\", \"电影版\", \"OVA版\", \"TV版\", \"新番动画\",\r\n\t\t\t\"完结动画\"\r\n\t\t],\r\n\t\t\"area\": [\"全部\", \"日本\", \"美国\", \"大陆\"],\r\n\t\t\"lang\": \"全部,国语,英语,粤语,闽南语,韩语,日语,其它\",\r\n\t\t\"year\": [\"全部\", \"2020\", \"2019\", \"2018\", \"2017\", \"2016\", \"2015\", \"2014\", \"2013\", \"2012\", \"2011\", \"2010\", \"2009\",\r\n\t\t\t\"2008\", \"2007\", \"2006\", \"2005\", \"2004\"\r\n\t\t]\r\n\t}\r\n}]"}
         * topic : {"wxAdId":{"ysId":"adunit-e9211ec2715058f8","cpId":"adunit-480cbd6aaa7ff814"},"tags":""}
         * play : {"isPoints":"1","enforce":"0","second":"","num":"","interval":0,"adMsg":{"start":"","close":""},"gg":{"radio":"0","type":"0","image":"","url":""},"wxAdId":{"jlspId":"adunit-c5ecca0f90e19f5a","cpId":"adunit-480cbd6aaa7ff814","spqtId":"adunit-4077ea75079b2394","ysId":"adunit-e9211ec2715058f8"},"vodPlayer":{"id":["qq","qiyi","youku","mgtv","dbm3u8","bjm3u8","tkm3u8","zuidam3u8"],"title":["tx线路","qy线路","yk线路","mg线路","db线路","bj线路","tk线路","zd线路"]}}
         * wode : {"gg":{"radio":"0","type":"2","image":"https://dl.yulu123.xyz/upload/vod/20201125-1/b0e8f9992929a88a39f6fad80b23d9c4.png","url":""},"statement":"","wxAdId":{"ysId":"adunit-e9211ec2715058f8"},"more":{"radio":"0"}}
         * other : {"wxAdId":{"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8"}}
         * wxverify : true
         * check : get
         */

        @JsonProperty("app")
        private String app;
        @JsonProperty("info")
        private String info;
        @JsonProperty("admin")
        private String admin;
        @JsonProperty("site")
        private SiteDTO site;
        @JsonProperty("index")
        private IndexDTO index;
        @JsonProperty("detail")
        private DetailDTO detail;
        @JsonProperty("feilei")
        private FeileiDTO feilei;
        @JsonProperty("topic")
        private TopicDTO topic;
        @JsonProperty("play")
        private PlayDTO play;
        @JsonProperty("wode")
        private WodeDTO wode;
        @JsonProperty("other")
        private OtherDTO other;
        @JsonProperty("wxverify")
        private Boolean wxverify;
        @JsonProperty("check")
        private String check;

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public SiteDTO getSite() {
            return site;
        }

        public void setSite(SiteDTO site) {
            this.site = site;
        }

        public IndexDTO getIndex() {
            return index;
        }

        public void setIndex(IndexDTO index) {
            this.index = index;
        }

        public DetailDTO getDetail() {
            return detail;
        }

        public void setDetail(DetailDTO detail) {
            this.detail = detail;
        }

        public FeileiDTO getFeilei() {
            return feilei;
        }

        public void setFeilei(FeileiDTO feilei) {
            this.feilei = feilei;
        }

        public TopicDTO getTopic() {
            return topic;
        }

        public void setTopic(TopicDTO topic) {
            this.topic = topic;
        }

        public PlayDTO getPlay() {
            return play;
        }

        public void setPlay(PlayDTO play) {
            this.play = play;
        }

        public WodeDTO getWode() {
            return wode;
        }

        public void setWode(WodeDTO wode) {
            this.wode = wode;
        }

        public OtherDTO getOther() {
            return other;
        }

        public void setOther(OtherDTO other) {
            this.other = other;
        }

        public Boolean getWxverify() {
            return wxverify;
        }

        public void setWxverify(Boolean wxverify) {
            this.wxverify = wxverify;
        }

        public String getCheck() {
            return check;
        }

        public void setCheck(String check) {
            this.check = check;
        }

        public static class SiteDTO {
            /**
             * message : {"radio":"1","tmpIds":"m9Tnwq3Ep3gNgKlhV8HYc1xc4Xk_15_-NbwPmP2tbI4"}
             * sharepic :
             * kefu : {"type":"1","url":"https://dl.yulu123.xyz/upload/vod/20201125-1/c33f4fbe36c815cacbfec5a826391350.jpeg"}
             * share : {"type":"0","wxText":""}
             * platform : 0
             * ip : {"radio":"1","text":"腾讯云"}
             * login : {"radio":"0","num":""}
             */

            @JsonProperty("message")
            private MessageDTO message;
            @JsonProperty("sharepic")
            private String sharepic;
            @JsonProperty("kefu")
            private KefuDTO kefu;
            @JsonProperty("share")
            private ShareDTO share;
            @JsonProperty("platform")
            private String platform;
            @JsonProperty("ip")
            private IpDTO ip;
            @JsonProperty("login")
            private LoginDTO login;

            public MessageDTO getMessage() {
                return message;
            }

            public void setMessage(MessageDTO message) {
                this.message = message;
            }

            public String getSharepic() {
                return sharepic;
            }

            public void setSharepic(String sharepic) {
                this.sharepic = sharepic;
            }

            public KefuDTO getKefu() {
                return kefu;
            }

            public void setKefu(KefuDTO kefu) {
                this.kefu = kefu;
            }

            public ShareDTO getShare() {
                return share;
            }

            public void setShare(ShareDTO share) {
                this.share = share;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public IpDTO getIp() {
                return ip;
            }

            public void setIp(IpDTO ip) {
                this.ip = ip;
            }

            public LoginDTO getLogin() {
                return login;
            }

            public void setLogin(LoginDTO login) {
                this.login = login;
            }

            public static class MessageDTO {
                /**
                 * radio : 1
                 * tmpIds : m9Tnwq3Ep3gNgKlhV8HYc1xc4Xk_15_-NbwPmP2tbI4
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("tmpIds")
                private String tmpIds;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getTmpIds() {
                    return tmpIds;
                }

                public void setTmpIds(String tmpIds) {
                    this.tmpIds = tmpIds;
                }
            }

            public static class KefuDTO {
                /**
                 * type : 1
                 * url : https://dl.yulu123.xyz/upload/vod/20201125-1/c33f4fbe36c815cacbfec5a826391350.jpeg
                 */

                @JsonProperty("type")
                private String type;
                @JsonProperty("url")
                private String url;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class ShareDTO {
                /**
                 * type : 0
                 * wxText :
                 */

                @JsonProperty("type")
                private String type;
                @JsonProperty("wxText")
                private String wxText;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getWxText() {
                    return wxText;
                }

                public void setWxText(String wxText) {
                    this.wxText = wxText;
                }
            }

            public static class IpDTO {
                /**
                 * radio : 1
                 * text : 腾讯云
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("text")
                private String text;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }

            public static class LoginDTO {
                /**
                 * radio : 0
                 * num :
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("num")
                private String num;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }
            }
        }

        public static class IndexDTO {
            /**
             * wxAdId : {"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8"}
             * tanchuang : {"radio":"0","type":"2","image":"","url":""}
             * share : {"title":"","image":""}
             * notice :
             * more : 1
             */

            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;
            @JsonProperty("tanchuang")
            private TanchuangDTO tanchuang;
            @JsonProperty("share")
            private ShareDTO share;
            @JsonProperty("notice")
            private String notice;
            @JsonProperty("more")
            private String more;

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public TanchuangDTO getTanchuang() {
                return tanchuang;
            }

            public void setTanchuang(TanchuangDTO tanchuang) {
                this.tanchuang = tanchuang;
            }

            public ShareDTO getShare() {
                return share;
            }

            public void setShare(ShareDTO share) {
                this.share = share;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getMore() {
                return more;
            }

            public void setMore(String more) {
                this.more = more;
            }

            public static class WxAdIdDTO {
                /**
                 * cpId : adunit-480cbd6aaa7ff814
                 * ysId : adunit-e9211ec2715058f8
                 */

                @JsonProperty("cpId")
                private String cpId;
                @JsonProperty("ysId")
                private String ysId;

                public String getCpId() {
                    return cpId;
                }

                public void setCpId(String cpId) {
                    this.cpId = cpId;
                }

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }
            }

            public static class TanchuangDTO {
                /**
                 * radio : 0
                 * type : 2
                 * image :
                 * url :
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("type")
                private String type;
                @JsonProperty("image")
                private String image;
                @JsonProperty("url")
                private String url;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class ShareDTO {
                /**
                 * title :
                 * image :
                 */

                @JsonProperty("title")
                private String title;
                @JsonProperty("image")
                private String image;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }
        }

        public static class DetailDTO {
            /**
             * play : {"radio":"0","APPID":""}
             * douban : 1
             * dbUpdate : 1
             * dbStills : 1
             * gg : {"radio":"0","type":"0","image":"","url":""}
             * wxAdId : {"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8","jlspId":""}
             * adMsg : {"start":"","close":""}
             */

            @JsonProperty("play")
            private PlayDTO play;
            @JsonProperty("douban")
            private String douban;
            @JsonProperty("dbUpdate")
            private String dbUpdate;
            @JsonProperty("dbStills")
            private String dbStills;
            @JsonProperty("gg")
            private GgDTO gg;
            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;
            @JsonProperty("adMsg")
            private AdMsgDTO adMsg;

            public PlayDTO getPlay() {
                return play;
            }

            public void setPlay(PlayDTO play) {
                this.play = play;
            }

            public String getDouban() {
                return douban;
            }

            public void setDouban(String douban) {
                this.douban = douban;
            }

            public String getDbUpdate() {
                return dbUpdate;
            }

            public void setDbUpdate(String dbUpdate) {
                this.dbUpdate = dbUpdate;
            }

            public String getDbStills() {
                return dbStills;
            }

            public void setDbStills(String dbStills) {
                this.dbStills = dbStills;
            }

            public GgDTO getGg() {
                return gg;
            }

            public void setGg(GgDTO gg) {
                this.gg = gg;
            }

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public AdMsgDTO getAdMsg() {
                return adMsg;
            }

            public void setAdMsg(AdMsgDTO adMsg) {
                this.adMsg = adMsg;
            }

            public static class PlayDTO {
                /**
                 * radio : 0
                 * APPID :
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("APPID")
                private String appid;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getAppid() {
                    return appid;
                }

                public void setAppid(String appid) {
                    this.appid = appid;
                }
            }

            public static class GgDTO {
                /**
                 * radio : 0
                 * type : 0
                 * image :
                 * url :
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("type")
                private String type;
                @JsonProperty("image")
                private String image;
                @JsonProperty("url")
                private String url;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class WxAdIdDTO {
                /**
                 * cpId : adunit-480cbd6aaa7ff814
                 * ysId : adunit-e9211ec2715058f8
                 * jlspId :
                 */

                @JsonProperty("cpId")
                private String cpId;
                @JsonProperty("ysId")
                private String ysId;
                @JsonProperty("jlspId")
                private String jlspId;

                public String getCpId() {
                    return cpId;
                }

                public void setCpId(String cpId) {
                    this.cpId = cpId;
                }

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }

                public String getJlspId() {
                    return jlspId;
                }

                public void setJlspId(String jlspId) {
                    this.jlspId = jlspId;
                }
            }

            public static class AdMsgDTO {
                /**
                 * start :
                 * close :
                 */

                @JsonProperty("start")
                private String start;
                @JsonProperty("close")
                private String close;

                public String getStart() {
                    return start;
                }

                public void setStart(String start) {
                    this.start = start;
                }

                public String getClose() {
                    return close;
                }

                public void setClose(String close) {
                    this.close = close;
                }
            }
        }

        public static class FeileiDTO {
            /**
             * wxAdId : {"ysId":"adunit-3a8a1b9ec6526a5c","cpId":"adunit-480cbd6aaa7ff814"}
             * type_class : [{
             "type_name": "连续剧",
             "type_id": "2",
             "type_extend": {
             "class": ["全部", "言情", "喜剧", "悬疑", "都市", "偶像", "古装", "军事", "警匪", "历史", "励志", "神话", "谍战", "青春", "家庭", "动作", "情景", "武侠",
             "科幻", "其他"
             ],
             "area": ["全部", "大陆", "美国", "香港", "台湾", "韩国", "泰国", "日本", "英国", "新加坡"],
             "lang": "全部,国语,英语,粤语,闽南语,韩语,日语,其它",
             "year": ["全部", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009",
             "2008", "2006", "2005", "2004"
             ]
             }
             }, {
             "type_name": "电影",
             "type_id": "1",
             "type_extend": {
             "class": ["全部", "喜剧", "爱情", "动作", "恐怖", "科幻", "剧情", "犯罪", "奇幻", "战争", "悬疑", "动画", "文艺", "伦理", "纪录", "传记", "歌舞", "古装",
             "历史", "惊悚", "其他"
             ],
             "area": ["全部", "大陆", "美国", "香港", "韩国", "日本", "法国", "英国", "德国", "台湾", "泰国", "印度", "其他"],
             "year": ["全部", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"]
             }
             }, {
             "type_name": "综艺",
             "type_id": "3",
             "type_extend": {
             "class": ["全部", "选秀", "情感", "访谈", "播报", "旅游", "音乐", "美食", "纪实", "曲艺", "生活", "游戏互动", "财经", "求职", "其他"],
             "area": ["全部", "内地", "港台", "日韩", "欧美", "其他"],
             "year": ["全部", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"]
             }
             }, {
             "type_name": "动漫",
             "type_id": "4",
             "type_extend": {
             "class": ["全部", "热血", "科幻", "美少女", "魔幻", "经典", "励志", "少儿", "冒险", "搞笑", "推理", "恋爱", "治愈", "幻想", "校园", "动物", "机战",
             "亲子", "儿歌", "运动", "悬疑", "怪物", "战争", "益智", "青春", "童话", "竞技", "动作", "社会", "友情", "真人版", "电影版", "OVA版", "TV版", "新番动画",
             "完结动画"
             ],
             "area": ["全部", "日本", "美国", "大陆"],
             "lang": "全部,国语,英语,粤语,闽南语,韩语,日语,其它",
             "year": ["全部", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009",
             "2008", "2007", "2006", "2005", "2004"
             ]
             }
             }]
             */

            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;
            @JsonProperty("type_class")
            private String typeClass;

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public String getTypeClass() {
                return typeClass;
            }

            public void setTypeClass(String typeClass) {
                this.typeClass = typeClass;
            }

            public static class WxAdIdDTO {
                /**
                 * ysId : adunit-3a8a1b9ec6526a5c
                 * cpId : adunit-480cbd6aaa7ff814
                 */

                @JsonProperty("ysId")
                private String ysId;
                @JsonProperty("cpId")
                private String cpId;

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }

                public String getCpId() {
                    return cpId;
                }

                public void setCpId(String cpId) {
                    this.cpId = cpId;
                }
            }
        }

        public static class TopicDTO {
            /**
             * wxAdId : {"ysId":"adunit-e9211ec2715058f8","cpId":"adunit-480cbd6aaa7ff814"}
             * tags :
             */

            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;
            @JsonProperty("tags")
            private String tags;

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public static class WxAdIdDTO {
                /**
                 * ysId : adunit-e9211ec2715058f8
                 * cpId : adunit-480cbd6aaa7ff814
                 */

                @JsonProperty("ysId")
                private String ysId;
                @JsonProperty("cpId")
                private String cpId;

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }

                public String getCpId() {
                    return cpId;
                }

                public void setCpId(String cpId) {
                    this.cpId = cpId;
                }
            }
        }

        public static class PlayDTO {
            /**
             * isPoints : 1
             * enforce : 0
             * second :
             * num :
             * interval : 0
             * adMsg : {"start":"","close":""}
             * gg : {"radio":"0","type":"0","image":"","url":""}
             * wxAdId : {"jlspId":"adunit-c5ecca0f90e19f5a","cpId":"adunit-480cbd6aaa7ff814","spqtId":"adunit-4077ea75079b2394","ysId":"adunit-e9211ec2715058f8"}
             * vodPlayer : {"id":["qq","qiyi","youku","mgtv","dbm3u8","bjm3u8","tkm3u8","zuidam3u8"],"title":["tx线路","qy线路","yk线路","mg线路","db线路","bj线路","tk线路","zd线路"]}
             */

            @JsonProperty("isPoints")
            private String isPoints;
            @JsonProperty("enforce")
            private String enforce;
            @JsonProperty("second")
            private String second;
            @JsonProperty("num")
            private String num;
            @JsonProperty("interval")
            private Integer interval;
            @JsonProperty("adMsg")
            private AdMsgDTO adMsg;
            @JsonProperty("gg")
            private GgDTO gg;
            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;
            @JsonProperty("vodPlayer")
            private VodPlayerDTO vodPlayer;

            public String getIsPoints() {
                return isPoints;
            }

            public void setIsPoints(String isPoints) {
                this.isPoints = isPoints;
            }

            public String getEnforce() {
                return enforce;
            }

            public void setEnforce(String enforce) {
                this.enforce = enforce;
            }

            public String getSecond() {
                return second;
            }

            public void setSecond(String second) {
                this.second = second;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public Integer getInterval() {
                return interval;
            }

            public void setInterval(Integer interval) {
                this.interval = interval;
            }

            public AdMsgDTO getAdMsg() {
                return adMsg;
            }

            public void setAdMsg(AdMsgDTO adMsg) {
                this.adMsg = adMsg;
            }

            public GgDTO getGg() {
                return gg;
            }

            public void setGg(GgDTO gg) {
                this.gg = gg;
            }

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public VodPlayerDTO getVodPlayer() {
                return vodPlayer;
            }

            public void setVodPlayer(VodPlayerDTO vodPlayer) {
                this.vodPlayer = vodPlayer;
            }

            public static class AdMsgDTO {
                /**
                 * start :
                 * close :
                 */

                @JsonProperty("start")
                private String start;
                @JsonProperty("close")
                private String close;

                public String getStart() {
                    return start;
                }

                public void setStart(String start) {
                    this.start = start;
                }

                public String getClose() {
                    return close;
                }

                public void setClose(String close) {
                    this.close = close;
                }
            }

            public static class GgDTO {
                /**
                 * radio : 0
                 * type : 0
                 * image :
                 * url :
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("type")
                private String type;
                @JsonProperty("image")
                private String image;
                @JsonProperty("url")
                private String url;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class WxAdIdDTO {
                /**
                 * jlspId : adunit-c5ecca0f90e19f5a
                 * cpId : adunit-480cbd6aaa7ff814
                 * spqtId : adunit-4077ea75079b2394
                 * ysId : adunit-e9211ec2715058f8
                 */

                @JsonProperty("jlspId")
                private String jlspId;
                @JsonProperty("cpId")
                private String cpId;
                @JsonProperty("spqtId")
                private String spqtId;
                @JsonProperty("ysId")
                private String ysId;

                public String getJlspId() {
                    return jlspId;
                }

                public void setJlspId(String jlspId) {
                    this.jlspId = jlspId;
                }

                public String getCpId() {
                    return cpId;
                }

                public void setCpId(String cpId) {
                    this.cpId = cpId;
                }

                public String getSpqtId() {
                    return spqtId;
                }

                public void setSpqtId(String spqtId) {
                    this.spqtId = spqtId;
                }

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }
            }

            public static class VodPlayerDTO {
                @JsonProperty("id")
                private List<String> id;
                @JsonProperty("title")
                private List<String> title;

                public List<String> getId() {
                    return id;
                }

                public void setId(List<String> id) {
                    this.id = id;
                }

                public List<String> getTitle() {
                    return title;
                }

                public void setTitle(List<String> title) {
                    this.title = title;
                }
            }
        }

        public static class WodeDTO {
            /**
             * gg : {"radio":"0","type":"2","image":"https://dl.yulu123.xyz/upload/vod/20201125-1/b0e8f9992929a88a39f6fad80b23d9c4.png","url":""}
             * statement :
             * wxAdId : {"ysId":"adunit-e9211ec2715058f8"}
             * more : {"radio":"0"}
             */

            @JsonProperty("gg")
            private GgDTO gg;
            @JsonProperty("statement")
            private String statement;
            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;
            @JsonProperty("more")
            private MoreDTO more;

            public GgDTO getGg() {
                return gg;
            }

            public void setGg(GgDTO gg) {
                this.gg = gg;
            }

            public String getStatement() {
                return statement;
            }

            public void setStatement(String statement) {
                this.statement = statement;
            }

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public MoreDTO getMore() {
                return more;
            }

            public void setMore(MoreDTO more) {
                this.more = more;
            }

            public static class GgDTO {
                /**
                 * radio : 0
                 * type : 2
                 * image : https://dl.yulu123.xyz/upload/vod/20201125-1/b0e8f9992929a88a39f6fad80b23d9c4.png
                 * url :
                 */

                @JsonProperty("radio")
                private String radio;
                @JsonProperty("type")
                private String type;
                @JsonProperty("image")
                private String image;
                @JsonProperty("url")
                private String url;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class WxAdIdDTO {
                /**
                 * ysId : adunit-e9211ec2715058f8
                 */

                @JsonProperty("ysId")
                private String ysId;

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }
            }

            public static class MoreDTO {
                /**
                 * radio : 0
                 */

                @JsonProperty("radio")
                private String radio;

                public String getRadio() {
                    return radio;
                }

                public void setRadio(String radio) {
                    this.radio = radio;
                }
            }
        }

        public static class OtherDTO {
            /**
             * wxAdId : {"cpId":"adunit-480cbd6aaa7ff814","ysId":"adunit-e9211ec2715058f8"}
             */

            @JsonProperty("wxAdId")
            private WxAdIdDTO wxAdId;

            public WxAdIdDTO getWxAdId() {
                return wxAdId;
            }

            public void setWxAdId(WxAdIdDTO wxAdId) {
                this.wxAdId = wxAdId;
            }

            public static class WxAdIdDTO {
                /**
                 * cpId : adunit-480cbd6aaa7ff814
                 * ysId : adunit-e9211ec2715058f8
                 */

                @JsonProperty("cpId")
                private String cpId;
                @JsonProperty("ysId")
                private String ysId;

                public String getCpId() {
                    return cpId;
                }

                public void setCpId(String cpId) {
                    this.cpId = cpId;
                }

                public String getYsId() {
                    return ysId;
                }

                public void setYsId(String ysId) {
                    this.ysId = ysId;
                }
            }
        }
    }
}
