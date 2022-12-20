package sejong.dormitory.domain.map.dormitoryPageController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.domain.map.dormitoryPageEntity.SejongDormitory;
import sejong.dormitory.domain.map.dormitoryPageService.SejongDormitoryService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SejongDormitoryController {
    private final SejongDormitoryService sejongDormitoryService;
    @GetMapping("/sejongDormitory")
    public String sejongDormitory(Model model) throws IOException {

        SejongDormitory data1 = sejongDormitoryService.getData();
        List<SejongDormitory> data2 = sejongDormitoryService.getData2();
        model.addAttribute("data1",data1);
        model.addAttribute("data2",data2);
        return "dormitoryPage/sejongDormitory";
    }
}
