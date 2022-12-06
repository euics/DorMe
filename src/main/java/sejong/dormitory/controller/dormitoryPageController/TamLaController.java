package sejong.dormitory.controller.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.entity.dormitoryPage.TamLa;
import sejong.dormitory.service.dormitoryPageService.TamLaService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TamLaController {
    private final TamLaService tamLaService;
    @GetMapping("/tamla")
    public String tamLa(Model model) throws IOException {
        List<TamLa> tamLaList = tamLaService.getDormitoryData();
        model.addAttribute("tamLaData", tamLaList);
        return "dormitoryPage/tamLa";
    }
}
