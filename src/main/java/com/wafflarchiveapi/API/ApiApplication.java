package com.wafflarchiveapi.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) throws IOException, GeneralSecurityException {

		SpringApplication.run(ApiApplication.class, args);

		GoogleSheet gs = new GoogleSheet();
		Object o = gs.getSheetValuesFromRange("1UyEK38Llr17ph_qrnuN2CUaKZitpXoKoR59uJqVqgIc", "Pick Owner!B3:B3")
				.get(0).get(0);
		System.out.println(o);
	}

}
