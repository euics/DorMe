package sejong.dormitory.service.dormitoryPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.entity.dormitoryPage.TamLa;
import sejong.dormitory.repository.dormitoryPage.TamLaRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TamLaService {
    private final String url = "https://www.jeju.go.kr/genius/guide/facility.htm";
    private final String baseUrl = "https://www.jeju.go.kr";

    private final TamLaRepository tamLaRepository;

    @PostConstruct
    public List<TamLa> getDormitoryData() throws IOException {
        List<TamLa> tamLaList = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();
        Elements sub_contents_clearfix = doc.getElementsByClass("facilities clearfix");

        for (Element element : sub_contents_clearfix) {
            Elements li = element.select("li");
            Elements strong = element.select("strong");
            String image_Url0 = li.get(0).select("img").attr("src");
            String image_Url1 = li.get(1).select("img").attr("src");

            String actual_Url0 = baseUrl + image_Url0;
            String actual_Url1 = baseUrl + image_Url1;

            TamLa dormitoryData0 = TamLa.builder()
                    .facility(strong.get(0).text())
                    .context(li.get(0).text())
                    .imagePath(image_Url0)
                    .detailImagePath(actual_Url0)
                    .build();
            tamLaList.add(dormitoryData0);
            tamLaRepository.save(dormitoryData0);

            TamLa dormitoryData1 = TamLa.builder()
                    .facility(strong.get(1).text())
                    .context(li.get(1).text())
                    .imagePath(image_Url1)
                    .detailImagePath(actual_Url1)
                    .build();
            tamLaList.add(dormitoryData1);
            tamLaRepository.save(dormitoryData1);
        }

        log.info("{}", tamLaList);

        return tamLaList;
    }
}