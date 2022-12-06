package sejong.dormitory.controller.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.dormitory.entity.dormitoryPage.ChongNam;
import sejong.dormitory.service.dormitoryPageService.ChongNamService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChongNamController {
    private final ChongNamService chongNamService;
    @GetMapping("/chongnam")
    public String chongNam(Model model) throws IOException {
        List<ChongNam> dormitoryData = chongNamService.getDormitoryData();
        model.addAttribute("chongnamData", dormitoryData);
        return "dormitoryPage/chongnam";
    }
}
