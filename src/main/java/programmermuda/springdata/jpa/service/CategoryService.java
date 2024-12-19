package programmermuda.springdata.jpa.service;

import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionOperations;
import programmermuda.springdata.jpa.entity.Category;
import programmermuda.springdata.jpa.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    public void error(){
        throw new RuntimeException("ups");
    }

    public void manual(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setTimeout(10);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(definition);
        try {
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category" + 1);
                categoryRepository.save(category);
            }
            error();
            platformTransactionManager.commit(transactionStatus);
        }catch(Throwable throwable){
            platformTransactionManager.rollback(transactionStatus);
            throw throwable;
        }

    }



    public void createCategories(){
        transactionOperations.executeWithoutResult(TransactionStatus ->{
            for (int i = 0; i < 5; i++) {
                Category category = new Category();
                category.setName("Category" + 1);
                categoryRepository.save(category);
            }
            error();
        });
    }

    @Transactional
    public void create(){
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName("Category" + 1);
            categoryRepository.save(category);
        }
        throw new RuntimeException("use rollback please");
    }

    public void test(){
        create();
    }

}
