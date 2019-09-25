//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.hibernate.dialect;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.engine.jdbc.dialect.internal.DialectResolverSet;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;

public class SQLiteMetadataBuilderInitializer implements MetadataBuilderInitializer {
  private static final SQLiteDialect dialect = new SQLiteDialect();
  private static final DialectResolver resolver = new DialectResolver() {
    public Dialect resolveDialect(DialectResolutionInfo info) {
      return info.getDatabaseName().equals("SQLite") ? SQLiteMetadataBuilderInitializer.dialect : null;
    }
  };

  public SQLiteMetadataBuilderInitializer() {
  }

  public void contribute(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
    DialectResolver dialectResolver = (DialectResolver)serviceRegistry.getService(DialectResolver.class);
    if (dialectResolver instanceof DialectResolverSet) {
      ((DialectResolverSet)dialectResolver).addResolver(resolver);
    }

  }
}
