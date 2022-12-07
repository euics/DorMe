package sejong.dormitory.controller.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.entity.dormitoryPage.gangwongwanak.GangwonGwanak0;
import sejong.dormitory.entity.dormitoryPage.gangwongwanak.GangwonGwanak1;
import sejong.dormitory.entity.dormitoryPage.gangwongwanak.GangwonGwanak2;
import sejong.dormitory.service.dormitoryPageService.GangwonGwanakService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GangwonGwanakController {
    private final GangwonGwanakService gangwonGwanakService;

    @GetMapping("/gangwonGwanak")
    public String gangwonGwanak(Model model) throws IOException {
        List<GangwonGwanak0> dormitoryData0 = gangwonGwanakService.findFromDB0();
        List<GangwonGwanak1> dormitoryData1 = gangwonGwanakService.findFromDB1();
        List<GangwonGwanak2> dormitoryData2 = gangwonGwanakService.findFromDB2();
        model.addAttribute("gangwonGwanakData0", dormitoryData0);
        model.addAttribute("gangwonGwanakData1", dormitoryData1);
        model.addAttribute("gangwonGwanakData2", dormitoryData2);
        return "dormitoryPage/gangwonGwanak";
    }
}
