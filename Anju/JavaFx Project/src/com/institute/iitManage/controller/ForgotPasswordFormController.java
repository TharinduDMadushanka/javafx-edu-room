package com.institute.iitManage.controller;

import com.institute.iitManage.util.tools.VerificationCodeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ForgotPasswordFormController {
    public TextField txtEmail;

    public void loginOnAction(ActionEvent actionEvent) {
    }

    public void sendVerificationOnAction(ActionEvent actionEvent) {
        //System.out.println(new VerificationCodeGenerator().getCode(5));
        int verificationCode = new VerificationCodeGenerator().getCode(5);

        //send mail basic requirements

        try {
            String fromEmail = "tharindudmadushanka920@gmail.com";
            String toEmail = txtEmail.getText();

            String host = "localhost";

            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);

            Session session = Session.getDefaultInstance(properties);

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.setSubject("Verification Code");
            mimeMessage.setText("Your verification code is : "+verificationCode);

            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            Transport.send(mimeMessage);

            System.out.println(verificationCode);

            FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("../view/CodeVerificationForm.fxml"));
            CodeVerificationFormController controller =fxmlLoader.getController();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
