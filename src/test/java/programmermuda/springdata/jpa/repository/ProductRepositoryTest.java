package programmermuda.springdata.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionOperations;
import programmermuda.springdata.jpa.entity.Category;
import programmermuda.springdata.jpa.entity.Product;
import programmermuda.springdata.jpa.model.ProductPrice;
import programmermuda.springdata.jpa.model.SimpleProduct;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void createProduct() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Apple Iphone 14 Pro Max");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
        {
            Product product = new Product();
            product.setName("Apple Iphone 13 Pro Max");
            product.setPrice(10_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

    }

    @Test
    void findByCategoryName() {
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH");
        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Apple Iphone 14 Pro Max", products.get(0).getName());
        Assertions.assertEquals("Apple Iphone 13 Pro Max", products.get(1).getName());
    }

    @Test
    void findProductSort() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", sort);

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Apple Iphone 13 Pro Max", products.get(0).getName());
        Assertions.assertEquals("Apple Iphone 14 Pro Max", products.get(1).getName());
    }

    @Test
    void findProductPageable() {
        // page 0
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals("Apple Iphone 13 Pro Max", products.getContent().get(0).getName());

        // page 1
        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(1, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals("Apple Iphone 14 Pro Max", products.getContent().get(0).getName());

    }

    @Test
    void testCount() {
        Long count = productRepository.count();
        Assertions.assertEquals(2, count);

        count = productRepository.countByCategory_Name("GADGET MURAH");
        Assertions.assertEquals(2, count);
    }

    @Test
    void testExists() {
        boolean exist = productRepository.existsByName("Apple Iphone 14 Pro Max");
        Assertions.assertTrue(exist);

        // test not exists
        exist = productRepository.existsByName("Apple bro");
        Assertions.assertFalse(exist);
    }

    @Test
    void testDelete() {

            Category category = categoryRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(category);

            Product product = new Product();
            product.setName("Samsung Galaxy S9");
            product.setPrice(4_000_000L);
            product.setCategory(category);
            productRepository.save(product);

            int delete = productRepository.deleteByName("Samsung Galaxy S9");
            Assertions.assertEquals(1, delete);

            // test not exists
            delete = productRepository.deleteByName("Samsung Galaxy S9");
            Assertions.assertEquals(0, delete);

    }

    @Test
    void searchProduct() {
        Pageable pageable = PageRequest.of(0,1);
        List<Product> products = productRepository.searchProductUsingName("Apple Iphone 14 Pro Max", pageable);
        Assertions.assertEquals(1, products.size() );
        Assertions.assertEquals("Apple Iphone 14 Pro Max", products.get(0).getName());
    }

    @Test
    void searchProductLike() {
        Pageable pageable = PageRequest.of(0,1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.searchProduct("%Iphone%", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());

        products = productRepository.searchProduct("%GADGET%", pageable);
        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());
    }

    @Test
    void testModifying() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            int total = productRepository.deleteProductUsingName("Wrong");
            Assertions.assertEquals(0, total);

            total = productRepository.updateProductPriceToZero(1L);
            Assertions.assertEquals(1, total);

            Product product = productRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(product);
            Assertions.assertEquals(0L, product.getPrice());
        });
    }

    @Test
    void stream() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(category);

            Stream<Product> stream = productRepository.streamAllByCategory(category);
            stream.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));
        });
    }

    @Test
    void slice() {
        Pageable firstPage = PageRequest.of(0,1);

        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        Slice<Product> slice = productRepository.findAllByCategory(category, firstPage);

        while (slice.hasNext()){
            slice = productRepository.findAllByCategory(category, slice.nextPageable());
        }
    }

    @Test
    void lock1() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try{
                Product product = productRepository.findFirstByIdEquals(1L)
                        .orElse(null);
                Assertions.assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            }catch (InterruptedException exception){
                throw new RuntimeException(exception);
            }
        });
    }

    @Test
    void lock2() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Product product = productRepository.findFirstByIdEquals(1L)
                    .orElse(null);
            Assertions.assertNotNull(product);
            product.setPrice(10_000_000L);
            productRepository.save(product);
        });
    }

    @Test
    void audit() {
        Category category = new Category();
        category.setName("Sampple Audit");
        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
        Assertions.assertNotNull(category.getCreatedDate());
        Assertions.assertNotNull(category.getLastModifiedDate());
    }

    @Test
    void example() {
        Category category = new Category();
        category.setName("GADGET MURAH");

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void exampleMatcher() {
        Category category = new Category();
        category.setName("gadget murah");

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase();

        Example<Category> example = Example.of(category, matcher);

        List<Category> categories = categoryRepository.findAll(example);
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void specification() {
        Specification<Product> specification = (root, criteria, builder) -> {
            return criteria.where(
                    builder.or(
                            builder.equal(root.get("name"), "Apple Iphone 14 Pro Max"),
                            builder.equal(root.get("name"), "Apple Iphone 13 Pro Max")
                    )
            ).getRestriction();
        };
        List<Product> products = productRepository.findAll(specification);
        Assertions.assertEquals(2, products.size());
    }

    @Test
    void projection() {
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        Assertions.assertEquals(2, simpleProducts.size());

        List<ProductPrice> productPrices = productRepository.findAllByNameLike("%Apple%", ProductPrice.class);
        Assertions.assertEquals(2, productPrices.size());
    }
}
