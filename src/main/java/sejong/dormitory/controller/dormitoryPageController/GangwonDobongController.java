package sejong.dormitory.controller.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.dto.dormitoryPage.GangwonDobong;
import sejong.dormitory.service.dormitoryPageService.GangwonDobongService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GangwonDobongController {
    private final GangwonDobongService gangwonDobongService;

    @GetMapping("/gangwonDobong")
    public String gangwonDobong(Model model) throws IOException{
        GangwonDobong data1 = gangwonDobongService.getData1();
        List<GangwonDobong> data2 = gangwonDobongService.getData2();
        model.addAttribute("data1",data1);
        model.addAttribute("data2",data2);
        return "dormitoryPage/gangwonDobong";
    }
}
