package sejong.dormitory.controller.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<GangwonGwanak> dormitoryData = gangwonGwanakService.findFromDB();
        model.addAttribute("gangwonGwanakData", dormitoryData);
        return "dormitoryPage/gangwonGwanak";
    }
}
