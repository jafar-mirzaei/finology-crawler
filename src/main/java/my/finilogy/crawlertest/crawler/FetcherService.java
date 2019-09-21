package my.finilogy.crawlertest.crawler;

import org.springframework.stereotype.Service;

import java.io.IOException;


public interface FetcherService {
  String fetchPage(String url) throws IOException;
  default void log(String msg, String... vals) {
    System.out.println(String.format(msg, vals));
  }
}
