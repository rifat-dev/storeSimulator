package com.rifat.storeSimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StoreSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreSimulatorApplication.class, args);
	}

	@GetMapping("/")
    public String hello() {
      return "hello";
    }

}
