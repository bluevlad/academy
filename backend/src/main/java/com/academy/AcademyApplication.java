package com.academy;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class AcademyApplication {

	@Value("${server.port:8080}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(AcademyApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void openBrowser() {
		String url = "http://localhost:" + serverPort + "/swagger-ui/index.html";
		String os = System.getProperty("os.name").toLowerCase();

		try {
			if (os.contains("win")) {
				// Windows - Chrome 브라우저 실행
				Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start", "chrome", url });
			} else if (os.contains("mac")) {
				// macOS
				Runtime.getRuntime().exec(new String[] { "open", "-a", "Google Chrome", url });
			} else if (os.contains("nix") || os.contains("nux")) {
				// Linux
				Runtime.getRuntime().exec(new String[] { "google-chrome", url });
			} else {
				// 기본 브라우저 사용
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(URI.create(url));
				}
			}
		} catch (IOException e) {
			System.out.println("브라우저 실행 실패: " + e.getMessage());
			// 기본 브라우저로 대체 시도
			try {
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(URI.create(url));
				}
			} catch (IOException ex) {
				System.out.println("기본 브라우저 실행 실패: " + ex.getMessage());
			}
		}
	}

}
