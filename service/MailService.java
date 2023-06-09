package am.itspace.taskmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailSender mailSender;
    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }
}

//    mailMessage.setFrom("anjelahovakimyan73@gmail.com");

//    new Thread(() -> mailSender.send(mailMessage)).start();

//    }
//    Thread thread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            mailSender.send(mailMessage);
//        }
//    });
//
//    thread.start();

