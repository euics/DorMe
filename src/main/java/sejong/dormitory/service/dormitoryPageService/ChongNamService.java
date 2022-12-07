package sejong.dormitory.service.dormitoryPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.entity.dormitoryPage.ChongNam;
import sejong.dormitory.repository.dormitoryPage.ChongNamRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ChongNamService {
    private final String url = "https://www.cninjae.or.kr/kr/html/sub09/090103.html";
    private final String baseUrl = "https://www.cninjae.or.kr";
    private final ChongNamRepository chongNamRepository;

    @PostConstruct @Transactional @Scheduled(cron = "0 0 18 * * *")
    public List<ChongNam> getDormitoryData() throws IOException{
        List<ChongNam> chongNamList = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();

        Elements tb1_basic_center = doc.getElementsByClass("tbl_basic center");
        Elements tbody = tb1_basic_center.select("tbody tr");
        Elements space_pic = doc.getElementsByClass("space_pic");
        Elements image = space_pic.select("ul li");

        for (Element element : tbody) {
            Elements facility = element.select("th");
            Elements context = element.select("td");

            ChongNam dormitoryData = ChongNam.builder()
                    .facility(facility.text())
                    .context(context.text())
                    .build();
            chongNamList.add(dormitoryData);
            chongNamRepository.save(dormitoryData);
        }

        for (Element element : image) {
            String image_Url = element.select("img").attr("src");
            String actual_Url = baseUrl + image_Url;

            ChongNam dormitoryData = ChongNam.builder()
                    .imagePath(image_Url)
                    .detailImagePath(actual_Url)
                    .build();
            chongNamList.add(dormitoryData);
            chongNamRepository.save(dormitoryData);
        }

        return chongNamList;
    }

    public List<ChongNam> findFromDB() throws IOException{
        return chongNamRepository.findAll();
    }
}
