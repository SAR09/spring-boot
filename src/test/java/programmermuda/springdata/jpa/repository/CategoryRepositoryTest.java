package programmermuda.springdata.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmermuda.springdata.jpa.entity.Category;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testInsert(){
        Category category = new Category();
        category.setName("GADGET");

        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());
    }

    @Test
    void testUpdate(){
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        category.setName("GADGET MURAH");
        categoryRepository.save(category);

        category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("GADGET MURAH", category.getName());
    }

    @Test
    void testQueryMethod() {
        Category category = categoryRepository.findFirstByNameEquals("GADGET MURAH").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals("GADGET MURAH", category.getName());

        List<Category> categories = categoryRepository.findAllByNameLike("%GADGET%");
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals("GADGET MURAH", categories.get(0).getName());
    }
}
