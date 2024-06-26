package com.yourlife.your.life.repository.finance;

import com.yourlife.your.life.model.entity.finance.Installment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface InstallmentRepository extends MongoRepository<Installment,String> {
    Optional<ArrayList<Installment>> findAllByUser_IdAndDeleted(String id,Boolean deleted);
}
