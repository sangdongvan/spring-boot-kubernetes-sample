package demo.api.v1;

import demo.catalog.Catalog;
import demo.catalog.CatalogInfo;
import demo.catalog.CatalogInfoRepository;
import demo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Service
public class CatalogServiceV1 {
    private CatalogInfoRepository catalogInfoRepository;
    private RestTemplate restTemplate;

    @Autowired
    public CatalogServiceV1(CatalogInfoRepository catalogInfoRepository,
                            RestTemplate restTemplate) {
        this.catalogInfoRepository = catalogInfoRepository;
        this.restTemplate = restTemplate;
    }

    public Catalog getCatalog() {
        Catalog catalog;

        CatalogInfo activeCatalog = catalogInfoRepository.findCatalogByActive(true);

        catalog = restTemplate.getForObject(String.format("http://localhost:18023/api/catalogs/search/findCatalogByCatalogNumber?catalogNumber=%s",
                activeCatalog.getCatalogId()), Catalog.class);

        ProductsResource products = restTemplate.getForObject(String.format("http://localhost:18023/api/catalogs/%s/products",
                catalog.getId()), ProductsResource.class);

        catalog.setProducts(products.getContent().stream().collect(Collectors.toSet()));
        return catalog;
    }

    public Product getProduct(String productId) {
        return restTemplate.getForObject(String.format("http://localhost:18023/v1/products/%s",
                productId), Product.class);
    }
}
