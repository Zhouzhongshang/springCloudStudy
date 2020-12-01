package com.zhou.servicefeign.controller.wjj;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhou.servicefeign.dao.dao.TestDbDao;
import com.zhou.servicefeign.pojo.dto.entity.TestDb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: sc-f-chapter1
 * @description: 测试
 * @author: zzs
 * @create: 2020-11-22 20:04
 **/
@RestController
@Slf4j
public class TestMydataBase implements Serializable {

    private static final long serialVersionUID = -8836891662583598196L;
    @Autowired
    private TestDbDao testDbDao;

    @GetMapping("test011")
    public List<TestDb> test(){
        TestDb testDb = new TestDb();
        testDb .setId(1);
return testDbDao.queryAll(testDb);
    }

    @PostMapping("insert01")
    @Transactional(rollbackFor = Exception.class)
    public void insert() throws Exception {
        TestDb testDb01 = new TestDb();
        testDb01.setIsDelete(0);
        testDb01.setCreateTime(LocalDateTime.now());
        testDb01.setUpdateTime(LocalDateTime.now());
        testDb01.setName("zzs");
        int insert1 = testDbDao.insert(testDb01);

        TestDb testDb02 = new TestDb();
        testDb02.setIsDelete(0);
        testDb02.setCreateTime(LocalDateTime.now());
        testDb02.setUpdateTime(LocalDateTime.now());
        testDb02.setName("zzs");
        int insert = testDbDao.insert(testDb02);
        if (insert==1){
            throw new Exception("主动跑出异常");
        }
    }

    public static void main(String[] args) throws IOException {
      //D:\CloudMusic\install\CloudMusic\cloudmusic.exe

        Runtime.getRuntime().exec("\"D:\\CloudMusic\\install\\CloudMusic\\cloudmusic.exe\""
                +"D:\\\\CloudMusic\\\\G.E.M.邓紫棋 - 孤独.mp3");

//        AudioInputStream as;
//        try {
//            //音频文件在项目根目录的img文件夹下
//            as = AudioSystem.getAudioInputStream(new File("D:\\CloudMusic\\G.E.M.邓紫棋 - 孤独.mp3"));
//            AudioFormat format = as.getFormat();
//            SourceDataLine sdl = null;
//            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
//            sdl = (SourceDataLine) AudioSystem.getLine(info);
//            sdl.open(format);
//            sdl.start();
//            int nBytesRead = 0;
//            byte[] abData = new byte[512];
//            while (nBytesRead != -1) {
//                nBytesRead = as.read(abData, 0, abData.length);
//                if (nBytesRead >= 0) {
//                    sdl.write(abData, 0, nBytesRead);
//                }
//            }
//            //关闭SourceDataLine
//            sdl.drain();
//            sdl.close();
//        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}
