package com.corphelper.mailservice;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MailCheckerService {

    private final Folder folder;

    @Value("${javax.mail.user}")
    private String user;

    @Value("${javax.mail.password}")
    private String password;

    @Value("${javax.mail.host}")
    private String host;

    @Value("${javax.mail.folder}")
    private String folderName;


    @SneakyThrows
    public void checkMail() {

        folder.open(Folder.READ_WRITE);
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

        System.out.println("Try to get messages");

        Message messages[] = folder.search(unseenFlagTerm);
        List<Message> messageList = Arrays.asList(messages);
        findAndSendAttachment(messageList);
        folder.close();

    }

    private void findAndSendAttachment(List<Message> messageList) throws IOException, MessagingException {

        for (Message message : messageList) {

            Multipart multipart = (Multipart) message.getContent();
            int countOfBodyParts = multipart.getCount();
            iterateMimeBodyParts(multipart, countOfBodyParts);

        }
    }

    private void iterateMimeBodyParts(Multipart multipart, int countOfBodyParts) throws MessagingException, IOException {

        for (int partCount = 0; partCount < countOfBodyParts; partCount++) {

            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);
            checkAttachment(part);

        }
    }

    private void checkAttachment(MimeBodyPart part) throws MessagingException, IOException {

        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {

            System.out.println("Try to save file");

            String fileName = part.getFileName();
            part.saveFile("E:\\" + File.separator + fileName);

        }
    }
}

