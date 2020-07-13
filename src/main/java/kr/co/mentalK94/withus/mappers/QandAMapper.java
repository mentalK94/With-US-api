package kr.co.mentalK94.withus.mappers;

import kr.co.mentalK94.withus.domains.Purchase;
import kr.co.mentalK94.withus.domains.PurchaseItem;
import kr.co.mentalK94.withus.domains.QuestionAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QandAMapper {

    void insertQuestion(QuestionAnswer questionAnswer);

    void insertAnswer(@Param("questionAnswerId")Long questionAnswerId,
                      @Param("answer")String answer, @Param("answerWriter")String answerWriter);

    List<QuestionAnswer> selectQuestionAnswer();

    List<QuestionAnswer> selectQuestionAnswerByProductId(Long productId);
}
