package systems.health263.dashboard.endpoint.config.app;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate(Client client, Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
//        return new ElasticsearchTemplate(client, new CustomEntityMapper(jackson2ObjectMapperBuilder.createXmlMapper(false).build()));
//    }
//
//    public class CustomEntityMapper implements EntityMapper {
//
//        private ObjectMapper objectMapper;
//
//        public CustomEntityMapper(ObjectMapper objectMapper) {
//            this.objectMapper = objectMapper;
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        }
//
//        @Override
//        public String mapToString(Object object) throws IOException {
//            return objectMapper.writeValueAsString(object);
//        }
//
//        @Override
//        public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
//            return objectMapper.readValue(source, clazz);
//        }
//    }
}
