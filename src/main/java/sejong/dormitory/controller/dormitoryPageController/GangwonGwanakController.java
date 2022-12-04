package sejong.dormitory.controller.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.entity.dormitoryPage.GangwonDobong;
import sejong.dormitory.entity.dormitoryPage.GangwonGwanak;
import sejong.dormitory.service.dormitoryPageService.GangwonGwanakService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GangwonGwanakController {
    private final GangwonGwanakService gangwonGwanakService;

    @GetMapping("/gangwonGwanak")
    public String gangwonGwanak(Model model) throws IOException {
        GangwonGwanak data1 = gangwonGwanakService.getData();
        List<GangwonGwanak> data2 = gangwonGwanakService.getData2();
        model.addAttribute("data1",data1);
        model.addAttribute("data2",data2);
        return "dormitoryPage/gangwonGwanak";
    }
}
