package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Mongo configuration class for this Spring Data MongoDB application.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
@Configuration
public class MongoConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new LongToDateTimeConverter());
        converterList.add(new StringToDateTimeConverter());
        return new MongoCustomConversions(converterList);
    }

    @ReadingConverter
    static class LongToDateTimeConverter implements Converter<Long, Date> {

        @Override
        public Date convert(Long source) {
            if (source == null) {
                return null;
            }

            return new Date(source);
        }
    }

    @ReadingConverter
    static class StringToDateTimeConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            if (source == null) {
                return null;
            }

            return new Date(source);
        }
    }
}
