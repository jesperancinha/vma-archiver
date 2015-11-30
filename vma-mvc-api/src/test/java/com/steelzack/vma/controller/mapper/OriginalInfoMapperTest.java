package com.steelzack.vma.controller.mapper;

import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.steelzack.vma.controller.AppConfig;
import com.steelzack.vma.controller.pojo.OriginalInfo;

public class OriginalInfoMapperTest implements OriginalInfoMapper {
	private Logger log = Logger.getLogger(OriginalInfoMapper.class.getName());

	@Override
	public void insertVillage(OriginalInfo village) {
		log.info(String.format("Title {0} has been persisted correctly", village.getTitle()));
	}

	@Test
	public void testMapping() throws Exception {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
			ctx.register(AppConfig.class);
			ctx.refresh();
			OriginalInfoMapper mapper = ctx.getBean(OriginalInfoMapper.class);
			OriginalInfo originalInfo = new OriginalInfo();
			originalInfo.setId(1);
			originalInfo.setTitle("This is a test title");
			mapper.insertVillage(originalInfo);
			System.out.println("---Original title saved---");
		}
	}
}
