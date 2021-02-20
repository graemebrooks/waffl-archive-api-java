package com.wafflarchiveapi.API;

import com.wafflarchiveapi.API.googlesheetsapi.GoogleSheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		SpringApplication.run(ApiApplication.class, args);
	}

}
