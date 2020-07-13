package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.QuestionAnswer;
import kr.co.mentalK94.withus.mappers.QandAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QAndAService {

    @Autowired
    QandAMapper qandAMapper;

    public void writeQuestion(QuestionAnswer questionAnswer) {
        qandAMapper.insertQuestion(questionAnswer);
    }

    public void writeAnswer(Long questionAnswerId, String answer, String answerWriter) {
        qandAMapper.insertAnswer(questionAnswerId, answer, answerWriter);
    }

    public List<QuestionAnswer> selectQuestionAnswerList() {
        // TODO: mapper에게 전체 Q&A list 요구
        return qandAMapper.selectQuestionAnswer();
    }

    public List<QuestionAnswer> selectQuestionAnswerListByProductId(Long productId) {
        // TODO: mapper에게 상품별 Q&A list 요구
        return qandAMapper.selectQuestionAnswerByProductId(productId);
    }
}
