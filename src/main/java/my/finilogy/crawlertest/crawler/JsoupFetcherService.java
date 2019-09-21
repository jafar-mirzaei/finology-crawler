package my.finilogy.crawlertest.crawler;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupFetcherService implements FetcherService {
  @Override public String fetchPage(final String url) throws IOException {

    Document doc = Jsoup.connect(url).get();
    log(doc.title());
    Elements newsHeadlines = doc.select("a");
    for (Element headline : newsHeadlines) {
      log("%s\n\t%s",
          headline.attr("title"), headline.absUrl("href"));
    }
    return doc.parser().toString();
  }
}
