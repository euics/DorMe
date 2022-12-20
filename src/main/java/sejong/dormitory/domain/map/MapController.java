package sejong.dormitory.domain.map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {

    @GetMapping("")
    public String entireMap(){
        return "map/entireMap";
    }

    @GetMapping("/loginMap")
    public String loginMap(Authentication authentication){
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e){
            return "redirect:/members/login";
        }
        return "map/loginMap";
    }
}
