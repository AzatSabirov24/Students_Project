package edu.javacourse.studentorder;

import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;

public class SaveOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    private static void checkAll() {

        while (true) {
            StudentOrder so = readStudentOrder();

            if (so == null) {
                break;
            }

            AnswerCityRegister cityAnswer = checkCityRegister(so);

//            if(!cityAnswer.success){
//                continue;
//            }

            AnswerWedding wedAnswer = checkWedding(so);
//            if(!wedAnswer.success){
//                break;
//            }
            AnswerChildren childAnswer = checkChildren(so);
//            if(!childAnswer.success){
//                break;
//            }
            AnswerStudent studentAnswer = checkStudent(so);
            if(!studentAnswer.success){
                break;
            }

            sendMail(so);
        }
    }

    static StudentOrder readStudentOrder() {
        StudentOrder so = new StudentOrder();
        return so;
    }

    static AnswerCityRegister checkCityRegister(StudentOrder so) {
        CityRegisterValidator crv1 = new CityRegisterValidator();
        crv1.hostName = "Host1";
        CityRegisterValidator crv2 = new CityRegisterValidator();
        crv2.hostName = "Host2";
        AnswerCityRegister ans1 = crv1.checkCityRegister(so);
        AnswerCityRegister ans2 = crv2.checkCityRegister(so);
        return ans1;
    }

    static AnswerWedding checkWedding(StudentOrder so) {
        WeddingValidator wv = new WeddingValidator();
        return wv.checkWedding(so);
    }

    static AnswerChildren checkChildren(StudentOrder so) {
        ChildrenValidator cv = new ChildrenValidator();
        return cv.checkChildren(so);
    }

    static AnswerStudent checkStudent(StudentOrder so) {
        return new StudentValidator().checkStudent(so);
    }

    static void sendMail(StudentOrder so) {
         new MailSender().sendMail(so);
    }
}
