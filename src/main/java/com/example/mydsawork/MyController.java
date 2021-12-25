package com.example.mydsawork;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MyController {

    @PostMapping("/work")
    public String Work(@RequestBody TheExpression expression){
        String[] strs = expression.getStr();
        System.out.println(expression.getStr());
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        String result = Calculator.calculate(strs);
        return result;
    }


}
