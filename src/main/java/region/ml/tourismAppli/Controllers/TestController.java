package region.ml.tourismAppli.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController

public class TestController {


    @GetMapping("/dataTest")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String, Object> dataTest(Authentication authentication){
        return Map.of("message",
                "Data test",
                "Username", authentication.getName(),
                "authorities",authentication.getAuthorities()
        );
    }

    @PostMapping("saveData")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String, String > saveData(String data){
        return Map.of("dataSaved",data);
    }

}
