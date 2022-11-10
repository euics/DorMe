package sejong.dormitory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sejong.dormitory.dto.GyeonggiDo;
import sejong.dormitory.service.GyeonggiDoService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GyeonggiDoController {
    private final GyeonggiDoService gyeonggiDoService;

    @GetMapping("/test")
    public String test(Model model) throws IOException{
        List<GyeonggiDo> gyeonggiDo = gyeonggiDoService.getData1();
        GyeonggiDo gyeonggiDo2 = gyeonggiDoService.getData2();
        model.addAttribute("img",gyeonggiDo);
        model.addAttribute("element",gyeonggiDo2);
        return "dormitoryPage/gyeonggiDo";
    }
}
