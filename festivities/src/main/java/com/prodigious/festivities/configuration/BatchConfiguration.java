package com.prodigious.festivities.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.prodigious.festivities.dto.FestivyDto;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @PersistenceContext
    public EntityManager em;
    
    @Autowired
    public DataSource dataSource;
    
    @Bean
    public StaxEventItemReader<FestivyDto> reader() {
        
        StaxEventItemReader<FestivyDto> reader = new StaxEventItemReader<>();
		reader.setResource(new ClassPathResource("festivities.xml"));
		reader.setFragmentRootElementName("festivity");
		Jaxb2Marshaller jx = new Jaxb2Marshaller();
		Class<?>[] clasesToBeBound = {FestivyDto.class};
		jx.setClassesToBeBound(clasesToBeBound);
		reader.setUnmarshaller(jx);
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<FestivyDto> writer() {
    	JdbcBatchItemWriter<FestivyDto> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<FestivyDto>());
        writer.setSql("INSERT INTO festivity (name, place, start, end) VALUES (:name, :place, :start, :end)");
        writer.setDataSource(dataSource);
        return writer;
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importFestivitiesJob() {
        return jobBuilderFactory.get("importFestivitiesJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<FestivyDto, FestivyDto> chunk(100)
                .reader(reader())
                .writer(writer())
                .build();
    }
    // end::jobstep
}
