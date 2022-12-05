//package sejong.dormitory.service.dormitoryPageService;
//
//import lombok.RequiredArgsConstructor;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import sejong.dormitory.entity.dormitoryPage.GyeonggiDo;
//import sejong.dormitory.repository.dormitoryPage.GyeonggiDoRepository;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Component
//public class GyeonggiDoService {
//    private static String URL1 = "http://www.gbfh.co.kr/0103/content/facilities/";
//    private static String URL2 = "http://www.gbfh.co.kr/0301/content/dormAbout/";
//    private final GyeonggiDoRepository gyeonggiDoRepository;
//
//    @Transactional
//    public GyeonggiDo getData(){
//        return gyeonggiDoRepository.findTopByOrderByIdDesc();
//    }
//    @Transactional @Scheduled(cron = "0 0 18 * * *")
//    public void getData2() throws IOException{
//        Document doc = Jsoup.connect(URL2).get();
//        String recruitPeriod = doc.selectXpath("/html/body/section/div/div/div/h5[8]/span").text();
//        String price = doc.selectXpath("/html/body/section/div/div/div/h5[12]").text();
//        String condition1 = doc.selectXpath("/html/body/section/div/div/div/h5[3]").text();
//        String condition2 = doc.selectXpath("/html/body/section/div/div/div/h5[4]").text();
//        String recruitMemberInfo = doc.selectXpath("/html/body/section/div/div/div/h5[1]").text();
//
//        GyeonggiDo gyeonggiDo = GyeonggiDo.builder()
//                .recruitPeriod(recruitPeriod)
//                .price(price)
//                .condition1(condition1)
//                .condition2(condition2)
//                .recruitMemberInfo(recruitMemberInfo)
//                .build();
//        gyeonggiDoRepository.save(gyeonggiDo);
//    }
//    @PostConstruct
//    public List<GyeonggiDo> getData1() throws IOException{
//        List<GyeonggiDo> imageSrc = new ArrayList<>();
//        Document doc = Jsoup.connect(URL1).get();
//        Elements elements = doc.select("div.small_image ul li");
//        for(Element element : elements){
//            GyeonggiDo gyeonggiDo = GyeonggiDo.builder()
//                    .detailImagePath("http://www.gbfh.co.kr" + element.select("a img").attr("src"))
//                    .build();
//            imageSrc.add(gyeonggiDo);
//        }
//        return imageSrc;
//    }
//}
