package com.dsusanibar.innovaxion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class InnovaxionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovaxionApplication.class, args);
	}
}

	@RestController
	@RequestMapping("/ix")
	class restServices {
		@RequestMapping("/time")
		public Map getTime(){
			Map map = new HashMap();

			map.put("Date", new Date());

			return map;
		}
	}