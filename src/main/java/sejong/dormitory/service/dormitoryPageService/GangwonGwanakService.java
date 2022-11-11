package sejong.dormitory.service.dormitoryPageService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import sejong.dormitory.dto.dormitoryPage.GangwonDobong;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GangwonGwanakService {
    private String URL1 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/enter_ga";
    private String URL2 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/facilities";
    private String URL3 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/amenities";
    private String URL4 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/scene";
    private String URL5 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/exercise";


    @PostConstruct
    public GangwonDobong getData1() throws IOException {
        Document doc = Jsoup.connect(URL1).get();
        Elements elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[1]/p[1]");
        String recruitPeriod = elements.text();

        List<String> recruitMember = new ArrayList<>();
        elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[2]/table/tbody");
        for (Element element : elements) {
            recruitMember.add(element.select("tr").text());
        }

        elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[3]/p");
        String condition1 = elements.text();

        elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[4]/p");
        String condition2 = elements.text();

        elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[10]/p");
        String joinPrice = elements.text();

        elements= doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[11]/p");
        String dormitoryPrice = elements.text();

        GangwonDobong gangwonDobong = GangwonDobong.builder()
                .recruitPeriod(recruitPeriod)
                .recruitMember(recruitMember)
                .condition1(condition1)
                .condition2(condition2)
                .joinPrice(joinPrice)
                .dormitoryPrice(dormitoryPrice)
                .build();
        return gangwonDobong;
    }

    @PostConstruct
    public List<GangwonDobong> getData2() throws IOException{
        List<GangwonDobong> imgSrc = new ArrayList<>();

        Document doc = Jsoup.connect(URL4).get();
        Elements elements = doc.selectXpath("//*[@id=\"facility\"]/article");
        for (Element element : elements) {
            GangwonDobong gangwonDobong = GangwonDobong.builder()
                    .detailImagePath("http://injae.gwd.go.kr/" +  element.select("figure img").attr("src"))
                    .build();
            imgSrc.add(gangwonDobong);
        }

        doc = Jsoup.connect(URL3).get();
        elements = doc.selectXpath("//*[@id=\"facility\"]/article");
        for (Element element : elements) {
            GangwonDobong gangwonDobong = GangwonDobong.builder()
                    .detailImagePath("http://injae.gwd.go.kr/" +  element.select("figure img").attr("src"))
                    .build();
            imgSrc.add(gangwonDobong);
        }

        doc = Jsoup.connect(URL2).get();
        elements = doc.selectXpath("//*[@id=\"facility\"]/article");
        for (Element element : elements) {
            GangwonDobong gangwonDobong = GangwonDobong.builder()
                    .detailImagePath("http://injae.gwd.go.kr/" +  element.select("figure img").attr("src"))
                    .build();
            imgSrc.add(gangwonDobong);
        }
        doc = Jsoup.connect(URL5).get();
        elements = doc.selectXpath("//*[@id=\"facility\"]/article");
        for (Element element : elements) {
            GangwonDobong gangwonDobong = GangwonDobong.builder()
                    .detailImagePath("http://injae.gwd.go.kr/" +  element.select("figure img").attr("src"))
                    .build();
            imgSrc.add(gangwonDobong);
        }
        return imgSrc;
    }
}
