package app.rijiben.blog.common.config;

import app.rijiben.blog.BlogStart;
import app.rijiben.blog.common.base.IEntity;
import app.rijiben.blog.common.consts.Constr;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = BlogStart.class)
@MapperScan(basePackages = Constr.MAPPER_SCAN)
@EnableTransactionManagement
public class WebConfig extends DelegatingWebMvcConfiguration {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean("dataSource")
    public DataSource dataSource(PropertyCfg propertyCfg) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(propertyCfg.getDriverClass());
        dataSource.setJdbcUrl(propertyCfg.getUrl());
        dataSource.setUsername(propertyCfg.getUsername());
        dataSource.setPassword(propertyCfg.getPassword());
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Constr.MAPPER_LOCATION));
        sqlSessionFactoryBean.setTypeAliasesPackage(Constr.TYPE_ALIASES_PACKAGE);
        sqlSessionFactoryBean.setTypeAliasesSuperType(IEntity.class);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public AuthConfig authConfig() {
        return new AuthConfig();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authConfig()).addPathPatterns(Constr.ADM_URL.concat("**")).excludePathPatterns(Constr.ADM_URL, Constr.ADM_URL.concat("login"), Constr.ADM_URL.concat("captcha"));
        super.addInterceptors(registry);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix(Constr.VIEW_PREFIX);
        resolver.setSuffix(Constr.VIEW_SUFFIX);
        resolver.setRequestContextAttribute("req");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
        super.configureViewResolvers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Constr.RESOURCE_PATH.concat("**")).addResourceLocations(Constr.RESOURCE_PATH);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter(Constr.DEFAULT_FMT));
        super.addFormatters(registry);
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        super.configureMessageConverters(converters);
    }
}
