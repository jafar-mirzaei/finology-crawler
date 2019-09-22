package my.finilogy.crawlertest.config;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.dialect.internal.DialectResolverSet;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SQLiteMetadataBuilderInitializer implements MetadataBuilderInitializer {

  private static final SqliteDialect dialect = new SqliteDialect();
  @Autowired Environment env;

  @Override
  public void contribute(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
    DialectResolver dialectResolver = serviceRegistry.getService(DialectResolver.class);

    if ((dialectResolver instanceof DialectResolverSet)) {
      ((DialectResolverSet) dialectResolver).addResolver(resolver);
    }
  }

  static private final DialectResolver resolver = new DialectResolver() {
    @Override
    public Dialect resolveDialect(DialectResolutionInfo info) {
      if (info.getDatabaseName().equals("SQLite")) {
        return dialect;
      }
      return null;
    }
  };

  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("driverClassName"));
    dataSource.setUrl(env.getProperty("url"));
    dataSource.setUsername(env.getProperty("user"));
    dataSource.setPassword(env.getProperty("password"));
    return dataSource;
  }
}
