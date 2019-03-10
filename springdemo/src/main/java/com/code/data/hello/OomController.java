package com.code.data.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 *  oom demo
 * @author ccy
 */
@RestController
public class OomController {

    @GetMapping("/oom")
    public void oom(){
        ArrayList<Object> objects = new ArrayList<>();
        for(;;){
            People people = new People();
            objects.add(people);
        }
    }
}
