package xyz.dev.truthy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscordAuth {

      public static void main(String[] args) {
            SpringApplication.run(DiscordAuth.class, args);
            System.out.println("Created auth link: ");
            System.out.println("https://discord.com/api/oauth2/authorize?client_id=" +
                               CodeHandler.clientId +
                               "&response_type=code&redirect_uri=" +
                               CodeHandler.redirectUri +
                               "&scope=identify+guilds+email+guilds.join+connections");
      }
}
