package sejong.dormitory.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import sejong.dormitory.dto.GyeonggiDo;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GyeonggiDoService {
    private static String URL1 = "http://www.gbfh.co.kr/0103/content/facilities/";
    private static String URL2 = "http://www.gbfh.co.kr/0301/content/dormAbout/";

    @PostConstruct
    public List<GyeonggiDo> getData1() throws IOException{
        List<GyeonggiDo> imageSrc = new ArrayList<>();
        Document doc = Jsoup.connect(URL1).get();
        Elements elements = doc.select("div.small_image ul li");
        for(Element element : elements){
            GyeonggiDo gyeonggiDo = GyeonggiDo.builder()
                    .detailImagePath("http://www.gbfh.co.kr" + element.select("a img").attr("src"))
                    .build();
            imageSrc.add(gyeonggiDo);
        }
        return imageSrc;
    }
    @PostConstruct
    public GyeonggiDo getData2() throws IOException{
        Document doc = Jsoup.connect(URL2).get();
        Elements elements1 = doc.select("div.cont");
        String tmp = elements1.select("h5.stt").toString();
        tmp = tmp.replace("<h5 class=\"stt\">","");
        tmp = tmp.replace("<h5 class=\"stt\">","");
        tmp = tmp.replace("<span class=\"bold_02\">","");
        tmp = tmp.replace("</span>","");
        tmp = tmp.replace("<span class=\"blue\">","");
        String[] str = tmp.split("</h5>");

        GyeonggiDo gyeonggiDo = GyeonggiDo.builder()
                .recruitPeriod(str[7])
                .price(str[11])
                .condition1(str[2])
                .condition2(str[3])
                .recruitMemberInfo(str[0])
                .build();
        return gyeonggiDo;
    }
}
