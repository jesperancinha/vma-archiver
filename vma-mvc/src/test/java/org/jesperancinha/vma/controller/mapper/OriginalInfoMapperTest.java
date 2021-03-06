package org.jesperancinha.vma.controller.mapper;

import org.jesperancinha.vma.controller.AppConfigTest;
import org.jesperancinha.vma.controller.AppTxConfig;
import org.jesperancinha.vma.entities.OriginalInfo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class OriginalInfoMapperTest implements OriginalInfoMapper {
    private static final Logger log = Logger.getLogger(OriginalInfoMapper.class.getName());

    @Override
    public void insertOriginalInfo(OriginalInfo village) {
        log.info(String.format("Title %s has been persisted correctly", village.getTitle()));
    }

    @Test
    public void testMapping() {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(AppConfigTest.class);
            ctx.register(AppTxConfig.class);
            ctx.refresh();
            OriginalInfoMapper mapper = ctx.getBean(OriginalInfoMapper.class);
            OriginalInfo originalInfo = new OriginalInfo();
            originalInfo.setId(1);
            originalInfo.setTitle("This is a test title");
            mapper.insertOriginalInfo(originalInfo);
            log.info("---Original title saved---");
            mapper.deleteAllOriginalInfo();
        }
    }

    @Test
    public void testMapping_List() {
        try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(AppConfigTest.class);
            ctx.register(AppTxConfig.class);
            ctx.refresh();
            final OriginalInfoMapper mapper = ctx.getBean(OriginalInfoMapper.class);
            mapper.deleteAllOriginalInfo();

            final int nElements = 3;
            Double avg = 0d;
            List<OriginalInfo> originalInfos = new ArrayList<OriginalInfo>();
            for (int currentElement = 0; currentElement < nElements; currentElement++) {
                OriginalInfo originalInfo = new OriginalInfo();
                originalInfo.setId(currentElement + 1);
                originalInfo.setTitle("This is a test title -" + UUID.randomUUID().toString());
                originalInfo.setDuration(new Random().nextDouble());
                mapper.insertOriginalInfo(originalInfo);
                log.info("---Original title saved---");

                originalInfos.add(originalInfo);
                avg += originalInfo.getDuration();
                log.info(String.format("Average for element %d was %f", currentElement, originalInfo.getDuration()));
            }
            avg = avg / nElements;

            Double resultAvg = originalInfos.stream(). //
                    mapToDouble(OriginalInfo::getDuration). //
                    average(). //
                    getAsDouble();

            assertEquals(avg, resultAvg);
            log.info("Avergage found was:" + resultAvg);
            mapper.deleteAllOriginalInfo();
        }
    }

    @Override
    public void deleteAllOriginalInfo() {
        log.info(String.format("All information as been deleted!"));
    }
}