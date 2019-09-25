package my.finilogy.crawlertest.crawler;


import com.google.gson.Gson;
import my.finilogy.crawlertest.persistance.ProductEntity;
import my.finilogy.crawlertest.persistance.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupFetcherService implements FetcherService {
  @Autowired
  private ProductRepository productRepository;

  @Override public String fetchPage(final String url) throws IOException {


    Document doc = Jsoup.connect(url).get();
    log(doc.title());
    Elements newsHeadlines = doc.select("img");
    Gson gson = new Gson();
    for (Element headline : newsHeadlines) {
//      log(gson.toJson(headline.chi));
      final ProductEntity s = new ProductEntity();
      s.setUrl(headline.absUrl("src"));
      s.setName(headline.val());
      productRepository.save(s);
      log("%s\n\t%s",
          headline.attr("class"), headline.absUrl("src"));
    }
    return doc.parser().toString();
  }
}
