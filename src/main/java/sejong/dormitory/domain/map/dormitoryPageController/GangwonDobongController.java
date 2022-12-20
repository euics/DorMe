package sejong.dormitory.domain.map.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.domain.map.dormitoryPageEntity.GangwonDobong;
import sejong.dormitory.domain.map.dormitoryPageService.GangwonDobongService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GangwonDobongController {
    private final GangwonDobongService gangwonDobongService;

    @GetMapping("/gangwonDobong")
    public String gangwonDobong(Model model) throws IOException{
        List<GangwonDobong> dormitoryData = gangwonDobongService.findFromDB();
        model.addAttribute("doBongData", dormitoryData);
        return "dormitoryPage/gangwonDobong";
    }
}
