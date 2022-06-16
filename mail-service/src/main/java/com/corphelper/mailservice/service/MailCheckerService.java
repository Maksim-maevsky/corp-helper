package com.corphelper.mailservice.service;

import com.corphelper.mailservice.pojo.FileInfo;
import com.corphelper.mailservice.pojo.MailInfo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            MailInfo mailInfo = new MailInfo();
            mailInfo.setSubject(message.getSubject());
            getFromLAndSetToMailInfo(message, mailInfo);
            Optional<FileInfo> optionalFileInfo = iterateMimeBodyParts(multipart);
            optionalFileInfo.ifPresent(mailInfo::setFileInfo);

            System.out.println(mailInfo);

        }
    }

    private void getFromLAndSetToMailInfo(Message message, MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException {

        Address ad = message.getFrom()[0];
        String from = MimeUtility.decodeText(ad.toString());
        mailInfo.setFrom(from);

    }

    private Optional<FileInfo> iterateMimeBodyParts(Multipart multipart) throws MessagingException, IOException {

        int countOfBodyParts = multipart.getCount();

        for (int partCount = 0; partCount < countOfBodyParts; partCount++) {

            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);

            if (isAttachment(part)) {

                System.out.println("Try to save file");
                FileInfo fileInfo = getFileInfo(part);

                return Optional.of(fileInfo);
            }
        }

        return Optional.empty();
    }

    private FileInfo getFileInfo(MimeBodyPart part) throws IOException, MessagingException {

        String fileName = part.getFileName();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setExtension(FilenameUtils.getExtension(fileName));
        fileInfo.setFileName(FilenameUtils.getBaseName(fileName));
        fileInfo.setFileBytes(part.getInputStream().readAllBytes());

        return fileInfo;
    }

    private boolean isAttachment(MimeBodyPart part) throws MessagingException {

        return Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition());
    }
}

