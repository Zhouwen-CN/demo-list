package com.yy.controller;

import com.yy.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: chen
 * @Date: 2022/10/13 21:55
 * @Desc:
 */
@Controller
public class MyController {
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private final AtomicReference<String> flag = new AtomicReference<>("");
    private final AtomicLong atomicLong = new AtomicLong(0);

    /**
     * 帮朋友写的,每天序列号得重置
     */
    @RequestMapping("/test")
    @ResponseBody
    public void test() {

        String dataDate = this.format.format(new Date());

        // 时间发生变化
        while (!flag.get().equals(dataDate)) {
            /*
             * 情况 1: 假设有两个线程同时进入
             *        第一个线程CAS成功,重置序列值
             *        第二个线程设置失败,重新开始循环,再次get equals相等,退出循环
             *        所需while循环需要动态获取eventDate的值
             *
             * 情况 2: 假设有两个线程同时进入
             *        第一个CAS成功,重置序列
             *        第二个才刚刚走到get,拿到的是最新的值,又CAS成功,重置序列
             *        所以if里面还需要判断两个时间不相等
             *  */
            String currentEventDate = flag.get();
            if (!currentEventDate.equals(dataDate) && flag.compareAndSet(currentEventDate, dataDate)) {
                atomicLong.set(0);
                break;
            }
        }

        System.out.printf("当前时间是: %s\t当前序列值:%s%n", flag, atomicLong.incrementAndGet());
    }


    @RequestMapping("/dataType")
    public String dataType(Model model) {

        model.addAttribute("flag", true);
        model.addAttribute("date", new Date());
        model.addAttribute("numbers", Arrays.asList(18, 10000, 5.5467));
        model.addAttribute("messages", Arrays.asList("hello", "Freemarker"));
        model.addAttribute("stars", new String[]{"周杰伦", "林俊杰", "五月天"});
        model.addAttribute("cities", Arrays.asList("北京", "上海", "深圳", "长沙"));
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "zhangsand", 21));
        users.add(new User(2, "lisi", 36));
        users.add(new User(3, "wangwu", 18));
        model.addAttribute("users", users);

        HashMap<String, String> map = new HashMap<>();
        map.put("sh", "上海");
        map.put("bj", "北京");
        map.put("sz", "深圳");
        model.addAttribute("map", map);


        return "dataType";
    }

    @GetMapping("/commend")
    public String commend() {
        return "commend";
    }

    @GetMapping("/import")
    public String import_template() {
        return "import";
    }

    @GetMapping("/include")
    public String include() {
        return "include";
    }

    @GetMapping("/news")
    @ResponseBody
    public String news(HttpServletRequest request) throws IOException {
        // 实例化模板对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置编码格式
        configuration.setDefaultEncoding("utf-8");
        // 获取基础路径
        File basePath = ResourceUtils.getFile("classpath:templates");
        // 设置base路径
        configuration.setDirectoryForTemplateLoading(basePath);
        // 获取模板
        Template template = configuration.getTemplate("news.ftl");

        // 封装model
        HashMap<String, String> map = new HashMap<>();
        map.put("title", "一个大龄单身女的心声");
        map.put("source", "怪咖");
        map.put("pubTime", "2022-04-15 21:57");
        map.put("content", "女，93年的，今年30岁了，单身 ，长的一般 工作一般，家庭条件一般，每个月都要去相亲，已经相到麻木，别人介绍不去吧说你傲，去了不行说你挑，人前说你眼光高，背后说你没人要！都说不要在乎别人的眼光，但谁又能真的不在乎，舌头根底下压死人，你不知道你在别人的嘴里扮演着多少个角色！有时候我真的想差不多就行了，但是真的身体是诚实的，连面对面坐着你都觉得恶心。妈妈的唠叨我已经习惯了，我可以不听可以不吵 可以回到我自己的房间，但是内心深处也觉得是自己真的挑吗？并不是，我也相到过很一般的人并且我觉得他不错想试着相处，但人家没看上我呀，咱也不知道哪里不行，但人家一说自己就不会再去贴了，也就随缘吧！\n" +
                "\n" +
                "我也被家里逼的在被窝里哭过，甚至想不辞而别，但我放不下我妈妈，我怕她自己一个人！有时候真的想如果就这样过一辈子的话会怎样，会老了死家里臭了都没人知道吗？会因为身体下不床连口水都喝不了吗？如果真的那样的话我宁愿吃点耗子药自己死了算了也不想活受罪！有时候我居然还想当个同妻，至少我完成任务了，过的好坏就都无所谓了，都不用催了，表面上我们都很好，那也不错呀！\n" +
                "\n" +
                "我想知道别人都是怎么做到的相一个就能妥，就能结婚生子的，我问就这么费劲呢");


        // render出去
        FileWriter fileWriter = null;
        try {
            File file = new File("news.html");
            fileWriter = new FileWriter(file);
            template.process(map, fileWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if (null != fileWriter) {
                fileWriter.close();
            }
        }

        return "success";
    }

}
