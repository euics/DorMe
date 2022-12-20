package sejong.dormitory.domain.map.dormitoryPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.domain.map.dormitoryPageEntity.GangwonDobong;
import sejong.dormitory.domain.map.dormitoryPageRepository.GangwonDobongRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GangwonDobongService {
    private final String url0 = "http://injae.gwd.go.kr/injae/gwdormitory/dobong/facility/facilities";
    private final String url1 = "http://injae.gwd.go.kr/injae/gwdormitory/dobong/facility/amenities";
    private final String baseUrl = "http://injae.gwd.go.kr";
    private final GangwonDobongRepository gangwonDobongRepository;

    @PostConstruct @Transactional @Scheduled(cron = "0 0 18 * * *")
    public void getDormitoryData() throws IOException {
        if(gangwonDobongRepository.existsById(1L))
            gangwonDobongRepository.deleteAll();

        Document doc0 = Jsoup.connect(url0).get();
        Elements class_content_pack0 = doc0.getElementsByClass("content-pack");
        Elements article0 = class_content_pack0.select("article");

        for (Element element : article0) {
            String image_Url = element.select("figure").select("img").attr("src");
            String actual_Url = baseUrl + image_Url;

            Elements figure_h3 = element.select("h3");

            Elements div_dl_dd = element.select("div dl dd");

            GangwonDobong dormitoryData = GangwonDobong.builder()
                    .facility0(figure_h3.get(0).text())
                    .context0(div_dl_dd.get(0).text())
                    .imagePath0(image_Url)
                    .detailImagePath0(actual_Url)
                    .build();
            gangwonDobongRepository.save(dormitoryData);
        }

        Document doc1 = Jsoup.connect(url1).get();
        Elements class_content_pack1 = doc1.getElementsByClass("content-pack");
        Elements article1 = class_content_pack1.select("article");

        for (Element element : article1) {
            String image_Url = element.select("figure").select("img").attr("src");
            String actual_Url = baseUrl + image_Url;

            Elements figure_h3 = element.select("h3");

            Elements div_dl_dd = element.select("div dl dd");

            GangwonDobong dormitoryData = GangwonDobong.builder()
                    .facility1(figure_h3.get(0).text())
                    .context1(div_dl_dd.get(2).text())
                    .imagePath1(image_Url)
                    .detailImagePath1(actual_Url)
                    .build();
            gangwonDobongRepository.save(dormitoryData);
        }
    }

    public List<GangwonDobong> findFromDB() throws IOException{
        return gangwonDobongRepository.findAll();
    }
}
