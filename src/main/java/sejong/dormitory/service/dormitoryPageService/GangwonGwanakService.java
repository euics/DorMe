package sejong.dormitory.service.dormitoryPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.entity.dormitoryPage.GangwonGwanak;
import sejong.dormitory.repository.dormitoryPage.GangwonGwanakRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class GangwonGwanakService {
    private final String url0 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/facilities";
    private final String url1 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/exercise";
    private final String url2 = "http://injae.gwd.go.kr/injae/gwdormitory/gwanag/facility/amenities";
    private final String baseUrl = "http://injae.gwd.go.kr";
    private final GangwonGwanakRepository gangwonGwanakRepository;

    @PostConstruct @Transactional @Scheduled(cron = "0 0 18 * * *")
    public void getDormitoryData() throws IOException {
        if(gangwonGwanakRepository.existsById(1L))
            gangwonGwanakRepository.deleteAll();

        Document doc0 = Jsoup.connect(url0).get();
        Elements class_content_pack0 = doc0.getElementsByClass("content-pack");
        Elements article0 = class_content_pack0.select("article");

        for (Element element : article0) {
            String image_Url = element.select("figure").select("img").attr("src");
            String actual_Url = baseUrl + image_Url;

            Elements figure_h3 = element.select("h3");

            Elements div_dl_dd = element.select("div dl dd");

            Elements div_ul_li = element.select("div ul li");

            if(!div_dl_dd.isEmpty()){
                GangwonGwanak dormitoryData = GangwonGwanak.builder()
                        .facility0(figure_h3.get(0).text())
                        .context0(div_dl_dd.get(0).text())
                        .imagePath0(image_Url)
                        .detailImagePath0(actual_Url)
                        .build();
                gangwonGwanakRepository.save(dormitoryData);
            }
            else{
                GangwonGwanak dormitoryData = GangwonGwanak.builder()
                        .facility0(figure_h3.get(0).text())
                        .context0(div_ul_li.text())
                        .imagePath0(image_Url)
                        .detailImagePath0(actual_Url)
                        .build();
                gangwonGwanakRepository.save(dormitoryData);
            }
        }

        Document doc1 = Jsoup.connect(url1).get();
        Elements class_content_pack1 = doc1.getElementsByClass("content-pack");
        Elements article1 = class_content_pack1.select("article");

        for (Element element : article1) {
            String image_Url = element.select("figure").select("img").attr("src");
            String actual_Url = baseUrl + image_Url;

            Elements figure_h3 = element.select("h3");

            Elements div_dl_dd = element.select("div dl dd");

            GangwonGwanak dormitoryData = GangwonGwanak.builder()
                    .facility1(figure_h3.get(0).text())
                    .context1(div_dl_dd.get(1).text())
                    .imagePath1(image_Url)
                    .detailImagePath1(actual_Url)
                    .build();
            gangwonGwanakRepository.save(dormitoryData);
        }

        Document doc2 = Jsoup.connect(url2).get();
        Elements class_content_pack2 = doc2.getElementsByClass("content-pack");
        Elements article2 = class_content_pack2.select("article");

        for (Element element : article2) {
            String image_Url = element.select("figure").select("img").attr("src");
            String actual_Url = baseUrl + image_Url;

            Elements figure_h3 = element.select("h3");

            Elements div_dl_dd = element.select("div dl dd");

            Elements div_ul_li = element.select("div ul li");

            if(!div_dl_dd.isEmpty()){
                GangwonGwanak dormitoryData = GangwonGwanak.builder()
                        .facility2(figure_h3.get(0).text())
                        .context2(div_dl_dd.get(1).text())
                        .imagePath2(image_Url)
                        .detailImagePath2(actual_Url)
                        .build();
                gangwonGwanakRepository.save(dormitoryData);
            }
            else{
                GangwonGwanak dormitoryData = GangwonGwanak.builder()
                        .facility2(figure_h3.get(0).text())
                        .context2(div_ul_li.get(0).text())
                        .imagePath2(image_Url)
                        .detailImagePath2(actual_Url)
                        .build();
                gangwonGwanakRepository.save(dormitoryData);
            }
        }
    }

    public List<GangwonGwanak> findFromDB() throws IOException{
        return gangwonGwanakRepository.findAll();
    }
}
