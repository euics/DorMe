package sejong.dormitory.service.dormitoryPageService;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.entity.dormitoryPage.GangwonDobong;
import sejong.dormitory.repository.dormitoryPage.GangwonDobongRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class GangwonDobongService {

    private String URL1 = "http://injae.gwd.go.kr/injae/gwdormitory/dobong/enter_db";
    private String URL2 = "http://injae.gwd.go.kr/injae/gwdormitory/dobong/facility/facilities";
    private String URL3 = "http://injae.gwd.go.kr/injae/gwdormitory/dobong/facility/amenities";
    private String URL4 = "http://injae.gwd.go.kr/injae/gwdormitory/dobong/facility/scene";
    private final GangwonDobongRepository gangwonDobongRepository;

    @Transactional
    public GangwonDobong getData() {
        return gangwonDobongRepository.findTopByOrderByIdDesc();
    }
    // 매일 오후 6시마다 웹 크롤링하여 데이터베이스 저장
    @Transactional @Scheduled(cron = "0 0 18 * * *")
    public void getData1() throws IOException {
        Document doc = Jsoup.connect(URL1).get();
        Elements elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[1]/p[1]");
        String recruitPeriod = elements.text();

//        List<String> recruitMember = new ArrayList<>();
//        elements = doc.selectXpath("//*[@id=\"content\"]/div/section/div/section/div[2]/table/tbody");
//        for (Element element : elements) {
//            recruitMember.add(element.select("tr").text());
//        }

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
           //     .recruitMember(recruitMember)
                .condition1(condition1)
                .condition2(condition2)
                .joinPrice(joinPrice)
                .dormitoryPrice(dormitoryPrice)
                .build();

        gangwonDobongRepository.save(gangwonDobong);
    }

    @PostConstruct
    public List<GangwonDobong> getData2() throws IOException{
        List<GangwonDobong> imgSrc = new ArrayList<>();

        Document doc = Jsoup.connect(URL2).get();
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

        doc = Jsoup.connect(URL4).get();
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
