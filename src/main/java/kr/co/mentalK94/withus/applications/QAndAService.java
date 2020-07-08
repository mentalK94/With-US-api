package kr.co.mentalK94.withus.applications;

import kr.co.mentalK94.withus.domains.QuestionAnswer;
import kr.co.mentalK94.withus.mappers.QandAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
