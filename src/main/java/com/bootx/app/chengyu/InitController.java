package com.bootx.app.chengyu;

import com.bootx.Demo;
import com.bootx.app.chengyu.entity.Idiom;
import com.bootx.app.chengyu.entity.IdiomLevel;
import com.bootx.app.chengyu.entity.Word;
import com.bootx.app.chengyu.service.IdiomLevelService;
import com.bootx.app.chengyu.service.IdiomService;
import com.bootx.app.chengyu.service.WordService;
import com.bootx.common.Result;
import com.bootx.entity.App;
import com.bootx.entity.SubscriptionTemplate;
import com.bootx.service.AppService;
import com.bootx.service.SubscriptionTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController("chengyuInitController")
@RequestMapping("/api/chengyu/init")
public class InitController {

    @Autowired
    private AppService appService;

    @Resource
    private SubscriptionTemplateService subscriptionTemplateService;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private IdiomLevelService idiomLevelService;

    @Resource
    private IdiomService idiomService;
    @Resource
    private WordService wordService;

    private static final Random RANDOM = new Random();

    @GetMapping
    public void init(Long id){
        App app = appService.find(2L);
        if(app!=null){
            Map<String, App.AdConfig> ads = new HashMap<>();

            //首页广告配置
            App.AdConfig indexAdConfig = ads.get("index");
            if(indexAdConfig==null){
                indexAdConfig = new App.AdConfig();

            }
            indexAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            indexAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            indexAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            indexAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            indexAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            indexAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            indexAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("index",indexAdConfig);

            App.AdConfig duihuanAdConfig = ads.get("duihuan");
            if(duihuanAdConfig==null){
                duihuanAdConfig = new App.AdConfig();

            }
            duihuanAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            duihuanAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            duihuanAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            duihuanAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            duihuanAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            duihuanAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            duihuanAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("duihuan",duihuanAdConfig);

            App.AdConfig gameAdConfig = ads.get("game");
            if(gameAdConfig==null){
                gameAdConfig = new App.AdConfig();
            }
            gameAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            gameAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            gameAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            gameAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            gameAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            gameAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            gameAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("game",gameAdConfig);

            App.AdConfig helpAdConfig = ads.get("help");
            if(helpAdConfig==null){
                helpAdConfig = new App.AdConfig();
            }
            helpAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            helpAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            helpAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            helpAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            helpAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            helpAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            helpAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("help",helpAdConfig);

            App.AdConfig jinbiAdConfig = ads.get("jinbi");
            if(jinbiAdConfig==null){
                jinbiAdConfig = new App.AdConfig();
            }
            jinbiAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            jinbiAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            jinbiAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            jinbiAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            jinbiAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            jinbiAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            jinbiAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("jinbi",jinbiAdConfig);

            App.AdConfig moreAdConfig = ads.get("more");
            if(moreAdConfig==null){
                moreAdConfig = new App.AdConfig();
            }
            moreAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            moreAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            moreAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            moreAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            moreAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            moreAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            moreAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("more",moreAdConfig);

            App.AdConfig rankAdConfig = ads.get("rank");
            if(rankAdConfig==null){
                rankAdConfig = new App.AdConfig();
            }
            rankAdConfig.setBannerId("adunit-5d392fcce4af1bf9");
            rankAdConfig.setGridAdId("adunit-177ef912ea58ca3e");
            rankAdConfig.setInterstitialAdId("adunit-f83096676f1a1054");
            rankAdConfig.setNativeAdId("adunit-4088233b68c4f746");
            rankAdConfig.setRewardedVideoAdId("adunit-1eefa901f0b541e0");
            rankAdConfig.setVideoAdId("adunit-1f98f08e663fa1b7");
            rankAdConfig.setVideoFrontAdId("adunit-03083c87b390182a");
            ads.put("rank",rankAdConfig);
            app.setAds(ads);
            appService.update(app);
        }
    }


    /**
     * 消息订阅
     * @param id
     * @param templateId
     */
    @GetMapping("/sub")
    public void sub(Long id,String templateId){
        App app = appService.find(id);
        if(app!=null){
            boolean exist = subscriptionTemplateService.exists(app,templateId);
            if(!exist){
                SubscriptionTemplate subscriptionTemplate = new SubscriptionTemplate();
                subscriptionTemplate.setTemplateId(templateId);
                subscriptionTemplate.setApp(app);
                Map<String, SubscriptionTemplate.Value> params = new HashMap<>();
                params.put("thing1",new SubscriptionTemplate.Value());
                params.put("thing2",new SubscriptionTemplate.Value());
                subscriptionTemplate.setParam(params);
                subscriptionTemplateService.save(subscriptionTemplate);
            }
            appService.update(app);
        }
    }

    @GetMapping("/idiom_init1")
    public void idiomInit1(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select word from chengyu_idiom where CHAR_LENGTH(word)=4");
        int level =1 ;
        for (Map<String,Object> map:list) {
            IdiomLevel idiomLevel = new IdiomLevel();
            idiomLevel.setPosition(3);
            idiomLevel.setLevel(level++);
            idiomLevel.setWord(map.get("word")+"");
            idiomLevelService.save(idiomLevel);
        }
    }

    @GetMapping("/idiom_random_level")
    public void idiom(){
        int max = 300000;
        int level = 1;
        int count = 1;
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT id,word FROM chengyu_idiom_level AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM chengyu_idiom_level)) AS id2) AS t2 WHERE t1.id >= t2.id2 and t1.level>"+max+" ORDER BY t1.id ASC LIMIT "+count);
        while (list.size()>0){
            for (Map<String,Object> map:list) {
                jdbcTemplate.update("update chengyu_idiom_level set level="+(level++)+",position="+RANDOM.nextInt(4)+" where id="+map.get("id"));
            }
            list = jdbcTemplate.queryForList("SELECT id,word FROM chengyu_idiom_level AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM chengyu_idiom_level)) AS id2) AS t2 WHERE t1.id >= t2.id2 and t1.level>"+max+" ORDER BY t1.id ASC LIMIT 1");
        }
    }



    @GetMapping("/idiom_answer")
    public void idiomAnswer(){
        List<IdiomLevel> idiomLevels = idiomLevelService.findAll();
        for (IdiomLevel idiomLevel:idiomLevels) {
            Integer position = RANDOM.nextInt(idiomLevel.getWord().length());
            String answer = idiomLevel.getWord().toCharArray()[position]+"";
            String s1 = jdbcTemplate.queryForObject("SELECT word FROM chengyu_word AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM chengyu_word)) AS id2) AS t2 WHERE t1.id >= t2.id2 ORDER BY t1.id ASC LIMIT 1", String.class);
            String s2 = jdbcTemplate.queryForObject("SELECT word FROM chengyu_word AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM chengyu_word)) AS id2) AS t2 WHERE t1.id >= t2.id2 ORDER BY t1.id ASC LIMIT 1", String.class);
            String s3 = jdbcTemplate.queryForObject("SELECT word FROM chengyu_word AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM chengyu_word)) AS id2) AS t2 WHERE t1.id >= t2.id2 ORDER BY t1.id ASC LIMIT 1", String.class);
            List<String> answers = new ArrayList<>();
            answers.add(answer);
            answers.add(s1);
            answers.add(s2);
            answers.add(s3);
            Collections.shuffle(answers);
            Collections.shuffle(answers);
            idiomLevel.setAnswers(answers);
            idiomLevel.setPosition(position);
            idiomLevelService.update(idiomLevel);




        }
    }


    @GetMapping("/idiom_init")
    public Result idiomInit(){
        List<Idiom> idioms = Demo.idiom();
        for (Idiom idiom:idioms) {
            idiomService.save(idiom);
        }

        return Result.success("ok");
    }

    @GetMapping("/word")
    public Result word(){
        List<Word> idioms = Demo.word();
        for (Word idiom:idioms) {
            wordService.save(idiom);
        }

        return Result.success("ok");
    }
}
