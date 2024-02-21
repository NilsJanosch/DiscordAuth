package xyz.dev.truthy;

import java.io.IOException;
import java.util.Objects;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeHandler {

      private static final Dotenv dotenv = Dotenv.load();
      public static final String clientId = dotenv.get("CLIENT_ID");
      public static final String clientSecret = dotenv.get("CLIENT_SECRET");
      public static final String botToken = dotenv.get("BOT_TOKEN");
      public static final String redirectUri = dotenv.get("REDIRECT");


      @GetMapping("/api/auth/discord/redirect")
      public String testGetData(
          @RequestParam("code") String code
      ) throws IOException {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                .add("client_id", Objects.requireNonNull(clientId))
                .add("client_secret", Objects.requireNonNull(clientSecret))
                .add("grant_type", "authorization_code")
                .add("code", code)
                .add("redirect_uri", Objects.requireNonNull(redirectUri))
                .build();

            Request request = new Request.Builder()
                .url("https://discord.com/api/v10/oauth2/token")
                .post(requestBody)
                .build();

            try (Response response = client.newCall(request).execute()) {
                  if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                  }

                  return response.body().string();
            }
      }
}