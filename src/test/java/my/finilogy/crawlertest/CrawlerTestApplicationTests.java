package my.finilogy.crawlertest;

import my.finilogy.crawlertest.crawler.FetcherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerTestApplicationTests {

  @Value(value = "${start.url}")
  private String startUrl;
  @Autowired
  private FetcherService fetcherService;

  @Test
  public void contextLoads() throws IOException {
    fetcherService.fetchPage(startUrl);
  }

}
