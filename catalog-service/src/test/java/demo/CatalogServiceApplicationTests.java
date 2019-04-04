package demo;


import demo.catalog.CatalogInfo;
import demo.catalog.CatalogInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatalogServiceApplication.class)
@ActiveProfiles(profiles = "default,test")
public class CatalogServiceApplicationTests {

    @Autowired
    private CatalogInfoRepository catalogInfoRepository;

    @Test
    public void createCatalogInfo() {
        CatalogInfo catalogInfo = new CatalogInfo(0L, true);
        catalogInfoRepository.save(catalogInfo);

        CatalogInfo actual = catalogInfoRepository.findCatalogByActive(true);

        Assert.assertEquals(catalogInfo, actual);
    }
}
