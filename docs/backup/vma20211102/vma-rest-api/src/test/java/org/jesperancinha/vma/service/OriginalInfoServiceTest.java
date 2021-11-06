package org.jesperancinha.vma.service;

import org.jesperancinha.vma.model.OriginalInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OriginalInfoService.class)
public class OriginalInfoServiceTest {

    @Autowired
    private OriginalInfoService originalInfoService;

    @Test
    public void testGetAll() {
        final Collection<OriginalInfo> all = originalInfoService.getAll();
        assertThat(all).hasSize(0);
    }
}