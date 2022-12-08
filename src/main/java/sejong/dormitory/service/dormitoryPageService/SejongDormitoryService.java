package sejong.dormitory.service.dormitoryPageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.entity.dormitoryPage.SejongDormitory;
import sejong.dormitory.repository.dormitoryPage.SejongDormitoryRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Component
public class SejongDormitoryService {

    private static String URL1 = "https://happydorm.sejong.ac.kr/30/3010.kmc";
    private static String URL2 = "https://happydorm.sejong.ac.kr/20/2020.kmc";
    private static String URL3 = "https://happydorm.sejong.ac.kr/20/2040.kmc";
    private final SejongDormitoryRepository sejongDormitoryRepository;

    @Transactional
    public SejongDormitory getData(){
        return sejongDormitoryRepository.findTopByOrderByIdDesc();
    }
    @PostConstruct @Transactional @Scheduled(cron = "0 0 18 * * *")
    public void getData1() throws IOException {

        Document doc = Jsoup.connect(URL1).get();

        String condition1 = doc.selectXpath("/html/body/div[4]/div/div[2]/div[2]/div[1]/div[3]/ul/li[1]/ul/li[1]").text();
        String condition2 = doc.selectXpath("/html/body/div[4]/div/div[2]/div[2]/div[1]/div[3]/ul/li[1]/ul/li[2]").text();
        String period1 = doc.selectXpath("//*[@id=\"wrap_con\"]/div/div[2]/div[2]/div[1]/div[3]/ul/li[3]/div/table/tbody/tr[1]/td[3]").text();
        String period2 = doc.selectXpath("/html/body/div[4]/div/div[2]/div[2]/div[1]/div[3]/ul/li[3]/div/table/tbody/tr[2]/td[2]").text();
        SejongDormitory sejongDormitory = SejongDormitory.builder()
                .condition1(condition1)
                .condition2(condition2)
                .period1("1학기 : "+period1)
                .period2("2학기 : "+period2)
                .build();
        sejongDormitoryRepository.save(sejongDormitory);
    }

    @PostConstruct
    public List<SejongDormitory> getData2() throws IOException {
        Document doc = Jsoup.connect(URL2).get();
        List<SejongDormitory> imgSrc = new ArrayList<>();
        Elements elements = doc.selectXpath("/html/body/div/div/ul");
        String img1 = "https://happydorm.sejong.ac.kr/resources/images/20/pop_img2020_01.jpg";
        String img2 = "https://happydorm.sejong.ac.kr/resources/images/20/pop_img2020_02.jpg";
        String img3 = "https://happydorm.sejong.ac.kr/resources/images/20/pop_img2020_03.jpg";
        String img4 = "https://happydorm.sejong.ac.kr/resources/images/20/pop_img2020_04.jpg";

        SejongDormitory sejongDormitory1 = SejongDormitory.builder()
                .detailImagePath(img1)
                .build();
        imgSrc.add(sejongDormitory1);

        SejongDormitory sejongDormitory2 = SejongDormitory.builder()
                .detailImagePath(img2)
                .build();
        imgSrc.add(sejongDormitory2);

        SejongDormitory sejongDormitory3 = SejongDormitory.builder()
                .detailImagePath(img3)
                .build();
        imgSrc.add(sejongDormitory3);

        SejongDormitory sejongDormitory4 = SejongDormitory.builder()
                .detailImagePath(img4)
                .build();
        imgSrc.add(sejongDormitory4);

        doc = Jsoup.connect(URL3).get();
        String img5 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_01.jpg";
        String img6 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_02.jpg";
        String img7 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_04.jpg";
        String img8 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_06.jpg";
        String img9 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_07.jpg";
        String img10 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_11.jpg";
        String img11 = "https://happydorm.sejong.ac.kr/resources/images/20/con_img2040_16.jpg";


        SejongDormitory sejongDormitory5 = SejongDormitory.builder()
                .detailImagePath(img5)
                .build();
        imgSrc.add(sejongDormitory5);

        SejongDormitory sejongDormitory6 = SejongDormitory.builder()
                .detailImagePath(img6)
                .build();
        imgSrc.add(sejongDormitory6);

        SejongDormitory sejongDormitory7 = SejongDormitory.builder()
                .detailImagePath(img7)
                .build();
        imgSrc.add(sejongDormitory7);

        SejongDormitory sejongDormitory8 = SejongDormitory.builder()
                .detailImagePath(img8)
                .build();
        imgSrc.add(sejongDormitory8);

        SejongDormitory sejongDormitory9 = SejongDormitory.builder()
                .detailImagePath(img9)
                .build();
        imgSrc.add(sejongDormitory9);

        SejongDormitory sejongDormitory10 = SejongDormitory.builder()
                .detailImagePath(img10)
                .build();
        imgSrc.add(sejongDormitory10);

        SejongDormitory sejongDormitory11 = SejongDormitory.builder()
                .detailImagePath(img11)
                .build();
        imgSrc.add(sejongDormitory11);
        return imgSrc;
    }
}
